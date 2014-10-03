import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;




import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Data {
	
	public static void main(String[] args) throws IOException
	{
		String baseUrl = "http://www.flipkart.com/google-nexus-5/product-reviews/ITMDZKKQHQXYC64R?pid=MOBDQ9VXZMHXZGBP&rating=1,2,3,4,5&reviewers=all&type=all&sort=most_helpful&start=";
		String temp;
		//System.out.println(baseUrl);
		int i=0;
		while(true)
			{
				temp = baseUrl + i;
				System.out.println(temp);
				if(DataFunnel(temp).equals(""))
					{	
						System.out.println("No More pages to crawl");
			        	System.out.println("Stop Page Link = "+temp);
			        	break;
			        }
				i=i+10;
			}
	}
	
	
	public static String DataFunnel(String link) {
	    org.jsoup.nodes.Document doc;
	    Elements reviews = null;
	    //String urlNextPage = new String();
	    try {
	    	PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true));
	        
	    	// need http protocol
	        doc = Jsoup.connect(link).timeout(25000).get();
	        reviews = doc.select("span[class=review-text]");
	        Elements date = doc.select("div[class=date line fk-font-small]");
	        Elements reviewHeading = doc.select("div[class=line fk-font-normal bmargin5 dark-gray]");
	        String title = doc.title();
	        
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
	    catch(NullPointerException n)
        {
        	System.out.println("No More pages to crawl");
        	System.out.println("Last Page Crawled = "+link);
        	System.exit(0);
        }
	    
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return (reviews.toString());
        
	}
}
