package publicDataTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import publicDataTest.controller.LandPriceRegisterManager;
import publicDataTest.model.LandPriceVO;
import publicDataTest.view.LANDPRICE_CHOICE;
import publicDataTest.view.MenuViewer;

public class PublicDataAPITest
{	public static Scanner sc = new Scanner(System.in);

	@SuppressWarnings("deprecation")
	public static void main (String [] args)
	{
		int no;
		boolean exitFlag = false; 
		LandPriceRegisterManager lrm = new LandPriceRegisterManager();
		while (!exitFlag)
		{
			try
			{
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine()); 
				switch (no)
				{
				case LANDPRICE_CHOICE.LOAD :
					ArrayList <LandPriceVO> landPriceVOList = apiDataLoad();
					printLandPriceList(landPriceVOList);
					break;
				case LANDPRICE_CHOICE.INSERT :
					lrm.insertManager();
					break;
				case LANDPRICE_CHOICE.SELECT :
					lrm.selectManager();
					break;
				case LANDPRICE_CHOICE.DELETE :
					lrm.deleteManager();
					break;
				case LANDPRICE_CHOICE.UPDATE :
					lrm.updateManager();
					break;
				case LANDPRICE_CHOICE.EXIT :
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true; 
					break; 
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e)
			{
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
		}
		//////////////////////////////////////////////////////////////////
		ArrayList <LandPriceVO> landPriceList = apiDataLoad();
		printLandPriceList(landPriceList);
	}	// end of main.
	
	public static void printLandPriceList(ArrayList <LandPriceVO> landPriceList)
	{
		for (LandPriceVO p : landPriceList)
		{
			System.out.println(p);
		}		
	}

	public static ArrayList <LandPriceVO> apiDataLoad()
	{
		// 공공데이터 가져오는법.
		// 1. url주소 기입.
		// 기존 방식.
//		String data = new String("가나다라");
//		data += "마바사";

		// 객체를 DB에서 가져오는게 아닌, 공공데이터 서버에서 -> 네트워크로 가져옴.
		ArrayList <LandPriceVO> landPriceList = new ArrayList <LandPriceVO>();
		
		StringBuilder urlBuilder = null;
		
		try
		{
			urlBuilder = new StringBuilder("https://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnNoList");	// StringBuilder는 삽입, 수정이 용이함.
			
			urlBuilder.append("?"+URLEncoder.encode("serviceKey", "UTF-8")+
					"=jDmsPkny9mVsxFweSI0yEqcXmfv6vINs%2B%2FqWgGsYdGueZh4PXGZsQPaASdNJAUV1Ga1Pl7C6VRzr%2Bjd%2B1rMePw%3D%3D");
			
			urlBuilder.append("&"+URLEncoder.encode("pageNo", "UTF-8")+"="+URLEncoder.encode("1", "UTF-8"));
			
			urlBuilder.append("&"+URLEncoder.encode("numOfRows", "UTF-8")+"="+URLEncoder.encode("10", "UTF-8"));
			
			urlBuilder.append("&"+URLEncoder.encode("_type", "UTF-8")+"="+URLEncoder.encode("xml", "UTF-8"));
			
			urlBuilder.append("&"+URLEncoder.encode("cityCode", "UTF-8")+"="+URLEncoder.encode("25", "UTF-8"));
			
			urlBuilder.append("&"+URLEncoder.encode("nodeNm", "UTF-8")+"="+URLEncoder.encode("전통시장", "UTF-8"));
			
			urlBuilder.append("&"+URLEncoder.encode("nodeNo", "UTF-8")+"="+URLEncoder.encode("44810", "UTF-8"));
			
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}	// UTF-8방식.
		
		// 2. 공공데이터에 connection객체 생성.
		URL url = null;
		HttpURLConnection conn = null;
		try
		{
			// 위에 url 적은 것. id, pw는 공공데이터이니 작성하지 않음. 
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		con = DriverManager.getConnection(url, id, pw);
			url = new URL(urlBuilder.toString());
				// http는 url을 상속받음. 따라서 캐스팅 작업이 필요함.
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");	// 메세지 헤더에 들어가는 요청방식.
			
			// 요청 성공 유무 conn.getResponseCode() 확인.
//			int responseCode = conn.getResponseCode();	// responseCode의 return값은 int임.
//			System.out.println("서버에서 보내준 요청에 대한 responseCode : " + responseCode);
			
			
		} catch (MalformedURLException e)
		{
			System.out.println(e.toString());
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
		
		// 서버에서 응답을 줄 때는 3가지 방식으로 줌. start line : 상태코드, message header : 서버의 상태정보, message body : html코드 및 데이터코드가 들어있음.
		// 3. 요청 전송, 응답 처리(200-300코드를 보여줄 시 : 정상, 403 : 권한설정, 404 : 페이지를 찾을 수 없음(Not Found), 405, : 인증이 안됨, 500 : 서버에서의 예외처리)
		
		
		BufferedReader br = null;	// message body의 메세지를 읽어오기 위해!
		
		try
		{
			int responseCode = conn.getResponseCode();
			
			// 성공 시 서버 요청에 성공 코드가 오면, message body를 읽기 위해 bufferedReader를 설정.
			if (responseCode >= 200 && responseCode <= 300)
			{
				InputStream is = conn.getInputStream();
				// 읽어와야하니까 from message body
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
			} else
			{	// 에러 발생 시.(200-300번 코드가 오면 실패.) -> message body에서 에러 메세지를 읽기 위해 준비.
				InputStream is = conn.getErrorStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
			}
			// message body에서 데이터를 가져오자.
			Document doc = parseXML(conn.getInputStream());
			
			// doc객체 속에서 item 태그명을 찾음. 그 아래 자식노드(tag객체) 값까지도 다 찾을 수가 있음.
			NodeList parentNode = doc.getElementsByTagName("item");
			
//			// 객체를 DB에서 가져오는게 아닌, 공공데이터 서버에서 -> 네트워크로 가져옴.
//			ArrayList <LandPriceVO> landPriceList = new ArrayList <LandPriceVO>();
			
			// parentNode객체에서 자식객체 가져오기.
			for (int i = 0; i < parentNode.getLength(); i++)
			{
				// 자식객체 가져오기.
				Node item = parentNode.item(i);
				LandPriceVO landPriceVO = new LandPriceVO();
				
				// 자식의 이름을 검색하여, 거기에 해당하는 데이터를 가져와 모델VO에 setters.
				// Node에는 또 다른 자식노드가 있을 수도 있음. 따라서 for문. <div 속성>context(내용 or 자식노드)</div>
				for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling())
				{
					switch (node.getNodeName())
					{									// 가져올 때 모두 문자열로 가져오기 때문에.
					case "gpslati" : landPriceVO.setGpslati(Double.parseDouble(node.getTextContent()));
						break;
						
					case "gpslong" : landPriceVO.setGpslong(Double.parseDouble(node.getTextContent()));
						break;
						
					case "nodeid" : landPriceVO.setNodeId(node.getTextContent());
						break;
						
					case "nodenm" : landPriceVO.setNodenm(node.getTextContent());
						break;
						
					case "nodeno" : landPriceVO.setNodeno(Integer.parseInt(node.getTextContent()));
						break;
					}
				}
				landPriceList.add(landPriceVO);
			}
//			for (LandPriceVO p : landPriceList)
//			{
//				System.out.println(p);
//			}
			
		} catch (IOException e)
		{
			
			
		}	// responseCode의 return값은 int임.
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if (conn != null)
			{
				conn.disconnect();
			}
		}
		
		return landPriceList;
		
	}

			// static을 안하면 108번 행에서 불러올 수 없음.
	public static Document parseXML(InputStream stream)
	{	// 입력에 해당되는 객체를 주면, Document로 변환하여 리턴.
        DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(stream);
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        { // Simple API for XML e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return doc;
    }
}
