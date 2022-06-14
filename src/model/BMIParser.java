package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class BMIParser {

	public BMI getBMI(String urlString) {
		BMI bmi = null;
		HttpURLConnection con = null;
		JsonReader reader = null;
		try {
			URL url= new URL(urlString);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			reader = new JsonReader(isr);
			bmi = new Gson().fromJson(reader,BMI.class);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(con != null) {
				con.disconnect();
			}
		}
		return bmi;
	}
}
