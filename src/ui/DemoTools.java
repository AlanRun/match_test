package ui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jdd.fm.core.exception.AesException;

import interfaceTest.LoginTest;

public class DemoTools {

	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("Auto Tools");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		// 添加面板
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void placeComponents(JPanel panel) {

		/*
		 * 布局部分我们这边不多做介绍 这边设置布局为 null
		 */
		panel.setLayout(null);

		// 创建 JLabel
		JLabel userLabel = new JLabel("PhoneNum:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width
		 * 和 height 指定新的大小。
		 */
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		final JTextField userText = new JTextField(20);
		userText.setText("13811110020");
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);

		// 输入密码的文本域
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);

		/*
		 * 这个类似用于输入的文本域 但是输入的信息会以点号代替，用于包含密码的安全性
		 */
		final JTextField passwordText = new JTextField(20);
		passwordText.setText("aaaaaa");
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);

		// 创建 JLabel
		JLabel cmdIdLabel = new JLabel("渠道名:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width
		 * 和 height 指定新的大小。
		 */
		cmdIdLabel.setBounds(10, 80, 80, 25);
		panel.add(cmdIdLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		final JTextField cmdIdText = new JTextField(20);
		cmdIdText.setText("app_zz");
		cmdIdText.setBounds(100, 80, 165, 25);
		panel.add(cmdIdText);

		// 创建 JLabel
		JLabel plantLabel = new JLabel("环境名:");
		/*
		 * 这个方法定义了组件的位置。 setBounds(x, y, width, height) x 和 y 指定左上角的新位置，由 width
		 * 和 height 指定新的大小。
		 */
		plantLabel.setBounds(10, 110, 80, 25);
		panel.add(plantLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		final JTextField plantText = new JTextField(20);
		plantText.setText("aoying");
		plantText.setBounds(100, 110, 165, 25);
		panel.add(plantText);

		// 创建登录按钮
		JButton loginButton = new JButton("注册");
		loginButton.setBounds(50, 140, 80, 25);
		panel.add(loginButton);
		
		// 创建登录按钮
		final JButton resultButton = new JButton("结果");
		resultButton.setBounds(150, 140, 80, 25);
		panel.add(resultButton);

		loginButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				resultButton.setText("--");
				
				String type = plantText.getText();
				String num = userText.getText();
				String pwd = passwordText.getText();
				String cmdName = cmdIdText.getText();
		
				try {
					boolean result = LoginTest.registerUseCmdName(type, num, pwd, cmdName);
					if (result) {
						resultButton.setText("成功！");
					} else {
						resultButton.setText("失败！");
					}
				} catch (AesException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
