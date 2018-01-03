package com.lewky.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 123 E-mail: lewkyliu@gmail.com
 * @version ����ʱ�䣺2017-4-1 ����4:27:09
 */
@WebServlet("/VerificationCodeServlet")
// �����֤��
public class VerificationCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// ��������
		int width = 100;
		int height = 23;

		Random r = new Random();
		// ����һ���ڴ�ͼ��
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��������
		Graphics g = image.getGraphics();

		// ����¾��εı���ɫ
		// �趨������ɫ
		g.setColor(Color.white);
		// �����εı���
		g.fillRect(1, 1, width-2, height-2);
		
		// ָ���߿����ɫ
		g.setColor(Color.gray);
		// ��ͼ��ı߿�
		g.drawRect(0, 0, width, height);
		
		// �趨������ɫ
		g.setColor(Color.gray);
		//��4��������
		for (int i = 0; i < 4; i++) {
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}


		// ��������
		g.setFont(new Font("��Բ", Font.BOLD + Font.ITALIC, 22));

		// �������4���ַ������ҳ��
		String s = "abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXYXZ3456789";
		String code = "";
		g.setColor(Color.blue);
		for (int i = 0; i < 4; i++) {
			char c = s.charAt(r.nextInt(s.length()));
			code = code + c + "";
			g.drawString(c + "", 17 * (i+1) + (r.nextBoolean()?1:-1) * 2, 18 + (r.nextBoolean()?1:-1) * 1);
		}
//		System.out.println(code);
		
		//��code�浽session������
		request.getSession().setAttribute("code", code);

		// ��ͼ��������ͻ���
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}