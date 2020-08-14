package crawnewsapp;
//1번 Jsoup (Maven)
//2번 Jsoup로 URL 요청
//3번 oid의 번호는 어디까지 있는지 
//oid마다의 신문사명을 매칭

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OidParse {
    
	Map<String, String> oidMap = new HashMap<>(); 
	static final int MAXERRORCOUNT = 100;
	
	public static void main(String[] args) {
		 
		OidParse oidParse = new OidParse();
		int count = 0;
		
		String oid = "001"; 
		String aid = "0000000001";
		String host = "https://news.naver.com/main/read.nhn?&sid1=101";
        String press; 
        String title; 
        String url; 
        Elements elTitle;
        Elements elPress;
		
        while (true) {
        	 url = host + "&oid=" + oid + "&aid=" + aid; 
        	 Document doc = null;
        	 try {
				   doc = Jsoup.connect(url).get(); 
				   elTitle = doc.select("#articleTitle"); 
				   elPress = doc.select(".article_header a img"); 
				   title = elTitle.text(); 
				   press = elPress.attr("title");
				   
				   if(!title.equals("")) {
					       oidParse.oidMap.put(oid, press);
					       
				   }else {
					   aid = MyUtil.increaseAid(aid);
					   //aid 증가 시키면서 찾아보기
					   doc = Jsoup.connect(url).get(); 
					   elTitle = doc.select("#articleTitle"); 
					   elPress = doc.select(".article_header a img"); 
					   title = elTitle.text(); 
					   press = elPress.attr("title");
					   
				   }
					System.out.println("==============================");
					System.out.println("oid : " + oid);
					System.out.println("elTitle : " + elTitle);
					System.out.println("elPress : " + elPress);
					System.out.println("title : " + title);
					System.out.println("press : " + press);
					System.out.println("url : " + url);
					System.out.println();
			} catch (Exception e) {
				   count++;
				   System.out.println("못찾았네 넘어가자!!");
			}
        	 oid = MyUtil.increaseOid(oid);
        	 
        	 if(MAXERRORCOUNT == count) break;
        }
        System.out.println("찾은 oid 개수는 : "+oidParse.oidMap.size());  
	}
}
