package com.acadgild.Assgn5Task1;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.acadgild.Assgn5Task1.Assgn5Task1.COUNTERS1;

public class Task1Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable value : values) {
        sum += value.get();
      }
      
      if(sum == 1)  
      {
    	  context.getCounter(COUNTERS1.RECORD_COUNTER).increment(1);
    	  context.write(key, new IntWritable(sum));
      }
    }
  }

