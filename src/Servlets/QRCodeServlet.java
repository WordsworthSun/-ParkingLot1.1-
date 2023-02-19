package Servlets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.swetake.util.Qrcode;

public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;  
    
    public QRCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String temp_id=new String(request.getParameter("temp_id").getBytes("ISO8859_1"),"UTF-8");
		String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
		String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
		String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
		String entry_date=new String(request.getParameter("entry_date").getBytes("ISO8859_1"),"UTF-8");
		String entry_time=new String(request.getParameter("entry_time").getBytes("ISO8859_1"),"UTF-8");
		String out_date=new String(request.getParameter("out_date").getBytes("ISO8859_1"),"UTF-8");
		String out_time=new String(request.getParameter("out_time").getBytes("ISO8859_1"),"UTF-8");
		String temp_money=new String(request.getParameter("temp_money").getBytes("ISO8859_1"),"UTF-8");			
		System.out.println(temp_id);
		String code=temp_id+card_id+car_num+seat_id+entry_date+entry_time+out_date+out_time+temp_money;
//		String code="临时编号为"+temp_id+"临时卡为"+card_id+"车牌号为"+car_num+"停车位为"+seat_id+"入场日期为"+entry_date+"入场时间为"+entry_time+"出场日期为"+out_date+ "出场时间为"+out_time+"停车费用"+temp_money;
//		String code=request.getParameter("code");
		
		Qrcode testQrcode=new Qrcode();
        //设置二维码的排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)
        //排错率越高能存储的信息越少，但对二维码的清晰度的要求越高
		testQrcode.setQrcodeErrorCorrect('L');
        //设置编码模式，N为数字，A为英文字母，B为二进制，K为汉字
		testQrcode.setQrcodeEncodeMode('K');
        //设置二维码尺寸，范围为1到40，值越大，尺寸越大，存储信息越多
		testQrcode.setQrcodeVersion(20);
		byte[] d=code.getBytes("UTF-8");
		BufferedImage image=new BufferedImage(100,100,BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g=image.createGraphics();
		g.setBackground(Color.white);
		g.clearRect(0, 0, 100, 100);
		g.setColor(Color.BLACK);
		if(d.length>0&&d.length<120)
		{
			boolean[][] s=testQrcode.calQrcode(d);
			for(int i=0;i<s.length;i++)
			{
				for(int j=0;j<s.length;j++)
				{
					if(s[j][i]){g.fillRect(j*2+3,i*2+3,2,2);}
				}
			}
		}
		g.dispose();
		image.flush();
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}       
}
