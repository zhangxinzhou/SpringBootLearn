package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonpTestApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		String target="http://ip.taobao.com/service/getIpInfo.php?ip=8.8.8.8";
		URL url=new URL(target);
		HttpURLConnection huc=(HttpURLConnection)url.openConnection();		
		huc.connect();
		BufferedReader br=new BufferedReader(new InputStreamReader(huc.getInputStream()));
		String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {// 循环读取流
            sb.append(line);
        }      
		System.out.println(sb.toString());
		br.close();//关闭流
        huc.disconnect();// 断开连接     
	}

}
