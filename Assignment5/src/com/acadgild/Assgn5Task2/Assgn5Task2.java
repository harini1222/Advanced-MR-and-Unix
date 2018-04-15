package com.acadgild.Assgn5Task2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Assgn5Task2 {

	// Declare a public static java enum to hold the values of the months
	public enum COUNTERS
	{
		RECORD_COUNTER;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		// Job Related Configurations
		Configuration conf = new Configuration();
		Job job = new Job(conf, "Assignment 5");
		job.setJarByClass(Assgn5Task2.class);

		job.setNumReduceTasks(0);

		// Set the mapper class
		job.setMapperClass(Task2Mapper.class);

		// Set the output key class and values
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//job.setReducerClass(Task2Reducer.class);

		// Set the path to take input file
		FileInputFormat.addInputPath(job, new Path(args[0]));

		// set the out path and delete it on the second run
		Path outputPath = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath, true);

		// Execute the job
		job.waitForCompletion(true);
		
		Counters counters = job.getCounters();
		System.out.println("number of times a song was heard fully :" 
		+ counters.findCounter(COUNTERS.RECORD_COUNTER).getValue());
		
		

	}
}