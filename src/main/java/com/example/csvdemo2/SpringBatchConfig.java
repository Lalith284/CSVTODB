package com.example.csvdemo2;



import java.io.IOException;
import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringBatchConfig {
	
	  @Autowired
	    private DataSource dataSource;
	  
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemProcessor<Sales, Sales> itemProcessor, ItemWriter<Sales> itemWritter,ItemReader<Sales> itemReader) 
	
	{
		Step step =stepBuilderFactory.get("CSV-FILE-LOAD")
				.<Sales, Sales>chunk(50000)
				.reader(itemReader)
				//.processor(itemProcessor)
				.writer(itemWritter)
				.taskExecutor(taskExecutor())
				.build();
		
		return jobBuilderFactory.get("CSV-LOAD")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
	}
	
	
	@Bean
	public FlatFileItemReader<Sales> itemReader(){
		
		FlatFileItemReader<Sales> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource("C:/Users/ELCOT/Desktop/csvdemo2/src/main/resources/fiveLakhs.csv"));
		flatFileItemReader.setName("CSV_READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	
	@Bean
	public JdbcBatchItemWriter<Sales> itemWriter(DataSource dataSource) {
		
	  return new JdbcBatchItemWriterBuilder<Sales>()
			  .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO salesrecord (orderid,region,country,itemtype,saleschannel,orderpriority,orderdate,shipdate,unitssold,unitprice,unitcost,totalrevenue,totalcost,totalprofit) VALUES (:orderId, :region, :country, :itemType, :salesChannel, :orderPriority, :orderDate, :shipDate, :unitsSold, :unitPrice, :unitCost, :totalRevenue, :totalCost, :totalProfit)")
	    .dataSource(dataSource)
	    .build();
		//System.err.println("AFTER");
	}

	
	
	@Bean 
	public LineMapper<Sales> lineMapper(){
		
		DefaultLineMapper<Sales> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTockenizer = new DelimitedLineTokenizer();
		
		lineTockenizer.setDelimiter(",");
		lineTockenizer.setStrict(false);
		lineTockenizer.setNames(new String[] {"region","country","itemType","salesChannel","orderPriority","orderDate","orderId","shipDate","unitsSold","unitPrice","unitCost","totalRevenue","totalCost","totalProfit"});
		
		BeanWrapperFieldSetMapper<Sales> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Sales.class);
		
		defaultLineMapper.setLineTokenizer(lineTockenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		return defaultLineMapper;
	}
	
	
	@Bean
	public TaskExecutor taskExecutor() {
//				SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
	//	taskExecutor.setConcurrencyLimit(5);
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor(); 
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	
	
	
//	@Bean
//	public ItemReader<Sales> FileNioReader(){
//		return new FileNioReader();
//	}
//	
		


//}
	
	
	
	
	
//	@Bean
//	ItemReader<? extends Sales> FileNioReader(){
//		return new FileNioReader();
//	}
	//	
//
//	@Autowired
//	public JobBuilderFactory jobBuilderFactory;
//
//	@Autowired
//	public StepBuilderFactory stepBuilderFactory;
//	
//	@Autowired
//	private ItemWriter<Sales> itemWriter;
//
//	@Autowired
//	private FlatFileItemReader<Sales> itemReader;
	
	
	
	

//	@Bean("partitioner")
//	@StepScope
//	public Partitioner partitioner() {
//
//		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		Resource[] resources = null;
//		try {
//			resources = resolver.getResources("/*.csv");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		partitioner.setResources(resources);
//		partitioner.partition(10);
//		return partitioner;
//	}
//	
//	
//	@Bean
//	public Job job(Step step1) {
//		return jobBuilderFactory.get("importUserJob")
//				.incrementer(new RunIdIncrementer())
//				.flow(masterStep())
//				.end()
//				.build();
//	}
//	

	

//	@Bean
//	@Qualifier("masterStep")
//	public Step masterStep() {
//		return stepBuilderFactory.get("masterStep")
//				.partitioner("step1", partitioner())
//				.step(step1())
//				.taskExecutor(taskExecutor())
//				.build();
//	}
//	
	
//	@Bean
//	public Step step1() {
//		return stepBuilderFactory.get("step1")
//				.<Sales, Sales>chunk(10)
//				//.processor(processor())
//				.reader(itemReader)
//				.writer(itemWriter)
//				//.skipLimit(1)
//				//.skip(Exception.class)
//				.build();
//	}
	
	
	
//	@Bean
//	public ThreadPoolTaskExecutor taskExecutor() {
//		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//		taskExecutor.setMaxPoolSize(4);
//		taskExecutor.setCorePoolSize(4);
//		taskExecutor.setQueueCapacity(5);
//		taskExecutor.setAllowCoreThreadTimeOut(true);
//		taskExecutor.afterPropertiesSet();
//		return taskExecutor;
//	}
	
	

//	@Bean
//	@StepScope
//	@Qualifier("itemReader")
//	@DependsOn("partitioner")
//	public FlatFileItemReader<Sales> itemtemReader(@Value("#{stepExecutionContext['fileName']}") String filename)
//			throws MalformedURLException {
//		//log.info("In Reader");
//		return new FlatFileItemReaderBuilder<Sales>().name("itemReader")
//				.delimited()
//				
//				.names(new String[] {"region","country","itemType","salesChannel","orderPriority","orderDate","orderId","shipDate","unitsSold","unitPrice","unitCost","totalRevenue","totalCost","totalProfit" })
//				.fieldSetMapper(new BeanWrapperFieldSetMapper<Sales>() {
//					{
//						setTargetType(Sales.class);
//					}
//				})
//				.resource(new UrlResource(filename))
//				.build();
//	}

//	
//	@Bean
//	@StepScope
//	@Qualifier("itemReader")
//	@DependsOn("partitioner")
//	public FlatFileItemReader<Sales> itemReader(@Value("#{stepExecutionContext['fileName']}") String filename)
//			throws MalformedURLException{
//		FlatFileItemReader<Sales> flatFileItemReader = new FlatFileItemReader<>();
//		flatFileItemReader.setResource(new UrlResource(filename));
//		flatFileItemReader.setName("CSV_READER");
//		flatFileItemReader.setLinesToSkip(1);
//		flatFileItemReader.setLineMapper(lineMapper());
//		
//		return flatFileItemReader;
//		
//	}
	
	
	

	


	
	
	
}

