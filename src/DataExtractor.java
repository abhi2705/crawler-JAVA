import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.mortbay.util.ajax.JSON;


public class DataExtractor {
	
	static List<String> revList = new ArrayList<String>();
	static List<String> dateList = new ArrayList<String>();;
	static List<String> titleList = new ArrayList<String>();;
	public static void main(String[] args) throws IOException, JSONException
	{
		String baseUrl = "http://www.flipkart.com/samsung-galaxy-note-2-n7100/product-reviews/ITMDHM3NUFYRRQKP?pid=MOBDDPH4CUB2Q3FU&rating=1,2,3,4,5&reviewers=all&type=top&sort=most_helpful&start=";
		String temp;
		//System.out.println(baseUrl);
		int i=420;

		JSONObject obj = new JSONObject();
		while(true)
			{
				temp = baseUrl + i;
				System.out.println(temp);
				if(DataFunnel(temp).equals(""))
					{	
						System.out.println("No More pages to crawl");
			        	System.out.println("Stop Page Link = "+temp);
			        	System.out.println("byeeeeeeeeeeeeee");
			        	break;
			        }
				i=i+10;
			}
		
		obj.put("Reviews", revList);
		obj.put("Review-Date", dateList);
		obj.put("Review-Title", titleList);
		
		PrintWriter writerjson = new PrintWriter(new FileWriter("note2-JSON.json", true));
		writerjson.println(obj);
		writerjson.close();
	}
	
	
	public static String DataFunnel(String link) {
	    org.jsoup.nodes.Document doc;
	    String reviews = null;
	    //String urlNextPage = new String();
	    try {
	    	PrintWriter writer = new PrintWriter(new FileWriter("note2.txt", true));
	        
	    	// need http protocol
	        doc = Jsoup.connect(link).timeout(25000).get();
	        reviews = doc.select("span[class=review-text]").text();
	        String date = doc.select("div[class=date line fk-font-small]").text();
	        String reviewHeading = doc.select("div[class=line fk-font-normal bmargin5 dark-gray]").text();
	        String title = doc.title();
	        
	        
	        revList.add(reviews);
	        dateList.add(date);
	        titleList.add(reviewHeading);
	        
	        
	        //System.out.println("-----------------------Reviews --------" + reviews);
	        //System.out.println("-----------------------Date --------" + date);
	        //System.out.println("-----------------------ReviewHeading --------" + reviewHeading);
	        
	        writer.println("Page Title : " + title);
	        writer.println("PresentPageURL = "+ link);
	        writer.println("-----------------------Reviews --------" + reviews);
	        writer.println("-----------------------Date --------" + date);
	        writer.println("-----------------------ReviewHeading --------" + reviewHeading);
	        
	        
	        
	        //urlNextPage = doc.select("a.nav_bar_next_prev").first().attr("abs:href");
	        //This is creating some issues, the loop ends coz of this
	        
	        
	        //System.out.println("NextPageURL = "+ urlNextPage);
	        
	        
	        
	        
	        //writer.println("NextPageURL = "+ urlNextPage);
	        
	        //Elements productList = doc.select("div[class=date line fk-font-small]");
	        //System.out.println("-----------------------Date --------" + productList);
	        // get page title
	        //System.out.println("title : " + title);

	        writer.close();
	        
	        // get all links
//	        Elements links = doc.select("a[href]");
//	        for (Element link : links) {
//	            // get the value from href attribute
//	            System.out.println("\nlink : " + link.attr("href"));
//	            System.out.println("text : " + link.text());
//	        }

	    } 
//	    catch(NullPointerException n)
//        {
//        	System.out.println("No More pages to crawl");
//        	System.out.println("Last Page Crawled = "+link);
//        	System.exit(0);
//        }
	    
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return (reviews.toString());
        
	}

}
