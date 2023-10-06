import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen extends JFrame{
	private final int FRAME_WIDTH = 1400;  //가로 크기
	private final int FRAME_HEIGHT = 900;  //세로 크기
	String imagePath = System.getProperty("user.dir")+"/src/images/";  //이미지 상대 경로
	ImageIcon startImg = new ImageIcon(imagePath + "Start_Screen.png"); //시작화면 이미지
	ImageIcon clickButtonImg = new ImageIcon(imagePath + "Click_Button.png");  //버튼 클릭 이미지
	
	JButton signInButton = new JButton("로그인",new ImageIcon(imagePath+"Button.png"));  //로그인 버튼
	JButton gameRuleButton = new JButton(new ImageIcon(imagePath+"GameRule_Button.png"));  //게임방법 버튼
	JButton signUpButton = new JButton("회원가입",new ImageIcon(imagePath+"Button.png"));  //회원가입 버튼
	
	JLabel startScreenLabel = new JLabel(startImg);  //시작화면 라벨
	JLabel logoTextLabel1 = new JLabel("MY", JLabel.CENTER);  //로고 라벨1  "MY"
	JLabel logoTextLabel2 = new JLabel("RHYTHM", JLabel.CENTER);  //로고 라벨2  "RHYTHM"
	JLabel logoTextLabel3 = new JLabel("NOTE", JLabel.CENTER);  //로고 라벨3  "NOTE"
	JLabel noPasswordLabel = new JLabel("<html><font color='red'>닉네임이나 비밀번호를 잘못 입력했어!</font></html>", JLabel.CENTER);  //불일치 안내 라벨
	
	JPanel startPanel = new JPanel();  //시작화면 패널
	JPanel signInPanel = new JPanel();  //로그인 패널
	JPanel signUpPanel = new JPanel();  //회원가입 패널
	JPanel gameRulePanel = new JPanel();  //게임방법 패널

	JTextField signInNickNameTF = new JTextField();  //로그인 닉네임 텍스트 필드
	JTextField signInPasswordTF = new JTextField();  //로그인 비밀번호 텍스트 필드
	
	Font font1 = new Font("TDTDTadakTadak",Font.PLAIN,250);   //로고 폰트
	Font buttonFont = new Font("TDTDTadakTadak",Font.PLAIN,80);   //버튼 폰트
	
	User user = new User();  //유저
	
	public Screen() {
		/*setBounds*/
		startPanel.setLayout(null);   //시작화면
		//로그인 버튼
		signInButton.setBounds(540,650,320,75);
		signInButton.setFont(buttonFont);
		signInButton.setHorizontalTextPosition(JButton.CENTER);
		signInButton.setRolloverIcon(clickButtonImg);  //호버링시 이미지 변경
		//회원가입 버튼
		signUpButton.setBounds(540,750,320,75);
		signUpButton.setFont(buttonFont);
		signUpButton.setHorizontalTextPosition(JButton.CENTER);
		signUpButton.setRolloverIcon(clickButtonImg);  //호버링시 이미지 변경
		//게임방법 버튼
		gameRuleButton.setBounds(300,500,150,150);
		gameRuleButton.setFont(buttonFont);
		gameRuleButton.setHorizontalTextPosition(JButton.CENTER);
		gameRuleButton.setActionCommand("게임방법");
		//로고 라벨1
		logoTextLabel1.setFont(font1);
		logoTextLabel1.setBounds(0, -300, FRAME_WIDTH, FRAME_HEIGHT);
		//로고 라벨2
		logoTextLabel2.setFont(font1);
		logoTextLabel2.setBounds(0, -100, FRAME_WIDTH, FRAME_HEIGHT);
		//로고 라벨3
		logoTextLabel3.setFont(font1);
		logoTextLabel3.setBounds(0, 100, FRAME_WIDTH, FRAME_HEIGHT);
		
		//시작화면 라벨
		startScreenLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		/*버튼 투명하게*/
		//로그인 버튼
		transparencyButton(signInButton);
		//회원가입 버튼
		transparencyButton(signUpButton);
		//게임방법 버튼
		transparencyButton(gameRuleButton);
		
		/*add*/
		signInButton.addActionListener(buttonListener);
		signUpButton.addActionListener(buttonListener);
		gameRuleButton.addActionListener(buttonListener);
		startPanel.add(signInButton);  //로그인 버튼
		startPanel.add(signUpButton);  //회원가입 버튼
		startPanel.add(gameRuleButton);  //게임방법 버튼
		startPanel.add(logoTextLabel1);  //로고 라벨1
		startPanel.add(logoTextLabel2);  //로고 라벨2
		startPanel.add(logoTextLabel3);  //로고 라벨3
		/* 얘네 위에 add 해야함*/
		startPanel.add(startScreenLabel);  //시작화면 라벨
		add(startPanel);  //시작화면 패널
		
		//기본 설정
		setTitle("Screen");  //제목 설정
		setSize(FRAME_WIDTH, FRAME_HEIGHT);  //사이즈 설정
		setLocationRelativeTo(null);  //윈도우 창 정중앙에
		setResizable(false);  //화면 크기 조정 불가
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//버튼 투명하게 만드는 메서드
	private void transparencyButton(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}
	
	//로그인 패널 만드는 메서드
	private void generateSignIn() {
		ImageIcon signInImg = new ImageIcon(imagePath + "Login_Screen.png");  //로그인 화면 이미지
		JLabel signInScreenLabel = new JLabel(signInImg);  //로그인 화면 라벨
		JLabel signInText = new JLabel("로그인");  //로그인 텍스트 라벨
		JLabel nickNameText = new JLabel("닉네임 : ");  //닉네임 텍스트 라벨
		JLabel passwordText = new JLabel("비밀번호 : ");  //비밀번호 텍스트 라벨
		
		Font font = new Font("TDTDTadakTadak",Font.PLAIN,200);  //폰트
		Font font2 = new Font("TDTDTadakTadak",Font.PLAIN,120);  // 조금 작은 폰트
		Font textFont = new Font("TDTDTadakTadak",Font.PLAIN,60);  //텍스트 필드용 폰트
		Font guideFont = new Font("TDTDTadakTadak",Font.PLAIN,40);  //비번 불일치 안내 폰트
		JButton OKButton = new JButton("확인!",new ImageIcon(imagePath+"Button.png"));  //확인 버튼
		
		/*set*/
		signInPanel.setLayout(null);  //로그인 화면 패널
		signInScreenLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);  //로그인 화면 라벨
		//로그인 텍스트 라벨
		signInText.setFont(font);
		signInText.setBounds(0, -350, FRAME_WIDTH, FRAME_HEIGHT);
		signInText.setHorizontalAlignment(JLabel.CENTER);
		//닉네임 텍스트 라벨
		nickNameText.setFont(font2);
		nickNameText.setBounds(200, 320, 600, 150);
		//비밀번호 텍스트 라벨
		passwordText.setFont(font2);
		passwordText.setBounds(105, 480, 600, 150);
		//닉네임 텍스트 필드
		signInNickNameTF.setFont(textFont);
		signInNickNameTF.setBounds(600, 355, 300, 80);
		//비밀번호 텍스트 필드
		signInPasswordTF.setFont(textFont);
		signInPasswordTF.setBounds(600, 515, 300, 80);
		//확인버튼
		OKButton.setFont(buttonFont);
		OKButton.setBounds(540,700,320,75);
		OKButton.setHorizontalTextPosition(JButton.CENTER);
		OKButton.setRolloverIcon(clickButtonImg);  //호버링시 이미지 변경
		transparencyButton(OKButton);  //버튼 투명하게
		//불일치 안내 라벨
		noPasswordLabel.setFont(guideFont);
		noPasswordLabel.setBounds(200, 600, 1000, 100);
		noPasswordLabel.setVisible(false);
		/*add*/
		signInPanel.add(noPasswordLabel);  //불일치 안내 라벨
		signInPanel.add(signInText);  //로그인 텍스트 라벨
		signInPanel.add(nickNameText);  //닉네임 텍스트 라벨
		signInPanel.add(passwordText);  //비번 텍스트 라벨
		signInPanel.add(signInNickNameTF);  //닉네임 텍스트 필드
		signInPanel.add(signInPasswordTF);  //비번 텍스트 필드
		
		signInPanel.add(OKButton);  //확인 버튼
		OKButton.addActionListener(buttonListener);  //test
		signInPanel.add(signInScreenLabel);
		add(signInPanel);
		signInPanel.setVisible(false);
	}
	//로그인 기능 메서드
	public void doSignIn() {
		DB db = new DB();
		db.connect();  //db 연결
		
		user.setNickName(signInNickNameTF.getText());  //닉네임 설정
		user.setPassword(signInPasswordTF.getText());  //비번 설정
		
		String sql = "SELECT * FROM user WHERE nickname = '" + user.getNickName() + "'";  //닉네임이 있는지 확인용 sql문
		ResultSet result;
		try {
			result = db.stmt.executeQuery(sql);
			if(result.next()) {  //닉네임이 있으면
				if(user.getPassword().equals(result.getString("password"))){ //입력한 비번과 계정 비번이 일치하면 로그인 성공
					//노래 선택 화면으로
				}else {
					noPasswordLabel.setVisible(true);  // 불일치 안내
				}
			}else {  //닉네임 없으면
				noPasswordLabel.setVisible(true);  // 불일치 안내
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();  //db 연결 해제
	}
	//버튼 액션리스너
	ActionListener buttonListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			startPanel.setVisible(false);  //시작화면 숨김
			if (e.getActionCommand().equals("로그인")) {
				generateSignIn();  //로그인 화면 생성
				signInPanel.setVisible(true);  //로그인 화면 보이게
            }
			if (e.getActionCommand().equals("회원가입")) {
               generateSignUp();  //회원가입 화면 생성
               signUpPanel.setVisible(true);  //회원가입 화면 보이게
            }
			if (e.getActionCommand().equals("게임방법")) {
            	generateGameRule();  //게임방법 화면 생성
            	gameRulePanel.setVisible(true);  //게임방법 화면 보이게
            } 
			if (e.getActionCommand().equals("확인!")) {
            	signUpPanel.setVisible(false);  //test
        		doSignIn();  //test
            }
			if (e.getActionCommand().equals("알겠어!")) {
            	gameRulePanel.setVisible(false);  //게임방법 화면 숨김
            	startPanel.setVisible(true);  //시작화면 보이게
            }
		}
	};
	
	//회원가입 패널 만드는 메서드
	private void generateSignUp() {
		ImageIcon signUpImg = new ImageIcon(imagePath + "Join_Screen.png");  //회원가입 화면 이미지
		JLabel signUpScreenLabel = new JLabel(signUpImg);  //회원가입 화면 라벨
		JLabel signUpText = new JLabel("회원가입");  //회원가입 텍스트 라벨
		JLabel nickNameText = new JLabel("닉네임 : ");  //닉네임 텍스트 라벨
		JLabel passwordText = new JLabel("비밀번호 : ");  //비밀번호 텍스트 라벨
		JLabel passwordCheckText = new JLabel("비밀번호 확인 : ");  //비밀번호 확인 텍스트 라벨
		
		JTextField nickNameTF = new JTextField();  //닉네임 텍스트 필드
		JTextField passwordTF = new JTextField();  //비밀번호 텍스트 필드
		JTextField passwordCheckTF = new JTextField();  //비밀번호 확인 텍스트 필드
		
		Font font = new Font("TDTDTadakTadak",Font.PLAIN,200);  //폰트
		Font font2 = new Font("TDTDTadakTadak",Font.PLAIN,100);  // 조금 작은 폰트
		Font textFont = new Font("TDTDTadakTadak",Font.PLAIN,60);  //텍스트 필드용 폰트
		
		JButton OKButton = new JButton("확인!",new ImageIcon(imagePath+"Button.png"));  //확인 버튼
		
		/*set*/
		signUpPanel.setLayout(null);  //회원가입 화면 패널
		signUpScreenLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);  //회원가입 화면 라벨
		//회원가입 텍스트 라벨
		signUpText.setFont(font);
		signUpText.setBounds(0, -350, FRAME_WIDTH, FRAME_HEIGHT);
		signUpText.setHorizontalAlignment(JLabel.CENTER);
		//닉네임 텍스트 라벨
		nickNameText.setFont(font2);
		nickNameText.setBounds(360, 225, 600, 150);
		//비밀번호 텍스트 라벨
		passwordText.setFont(font2);
		passwordText.setBounds(285, 370, 600, 150);
		//비밀번호 확인 텍스트 라벨
		passwordCheckText.setFont(font2);
		passwordCheckText.setBounds(105, 515, 600, 150);
		//닉네임 텍스트 필드
		nickNameTF.setFont(textFont);
		nickNameTF.setBounds(700, 260, 300, 80);
		//비밀번호 텍스트 필드
		passwordTF.setFont(textFont);
		passwordTF.setBounds(700, 405, 300, 80);
		//비밀번호 확인 텍스트 필드
		passwordCheckTF.setFont(textFont);
		passwordCheckTF.setBounds(700, 550, 300, 80);
		//확인버튼
		OKButton.setFont(buttonFont);
		OKButton.setBounds(540,700,320,75);
		OKButton.setHorizontalTextPosition(JButton.CENTER);
		OKButton.setRolloverIcon(clickButtonImg);  //호버링시 이미지 변경
		transparencyButton(OKButton);  //버튼 투명하게
		/*add*/
		signUpPanel.add(signUpText);  //로그인 텍스트 라벨
		signUpPanel.add(nickNameText);  //닉네임 텍스트 라벨
		signUpPanel.add(passwordText);  //비번 텍스트 라벨
		signUpPanel.add(passwordCheckText);  //비번 확인 텍스트 라벨
		signUpPanel.add(nickNameTF);  //닉네임 텍스트 필드
		signUpPanel.add(passwordTF);  //비번 텍스트 필드
		signUpPanel.add(passwordCheckTF);  //비번 확인 텍스트 필드
		signUpPanel.add(OKButton);  //확인 버튼
		OKButton.addActionListener(buttonListener);  //test
		signUpPanel.add(signUpScreenLabel);
		add(signUpPanel);
		signUpPanel.setVisible(false);
	}
	//게임방법 패널 만드는 메서드
	private void generateGameRule() {
		ImageIcon gameRuleImg = new ImageIcon(imagePath + "GameRule_Screen.png");  //게임방법 화면 이미지
		
		JLabel gameRuleScreenLabel = new JLabel(gameRuleImg);  //게임방법 화면 라벨
		JLabel logoTextLabel1 = new JLabel("MY", JLabel.CENTER);  //로고 라벨1  "MY"
		JLabel logoTextLabel2 = new JLabel("RHYTHM", JLabel.CENTER);  //로고 라벨2  "RHYTHM"
		JLabel logoTextLabel3 = new JLabel("NOTE", JLabel.CENTER);  //로고 라벨3  "NOTE"
		JLabel gameRuleText = new JLabel("게임방법!");  //게임방법 텍스트 라벨
		JLabel gameRuleLabel1 = new JLabel("조작키 S  D  F  J  K  L",JLabel.CENTER);  //게임방법 라벨 1
		JLabel gameRuleLabel2 = new JLabel("1. 내려오는 노트에 맞춰 키보드를 눌러보자!",JLabel.CENTER);  //게임방법 라벨 2
		JLabel gameRuleLabel3 = new JLabel("<html>2. 정확도에 따라 <font color=#00BFFF'>PERFECT</font>, <font color='#04B404'>GOOD</font>, <font color='red'>BAD</font>로 판정돼.</html>",JLabel.CENTER);  //게임방법 라벨 3
		JLabel gameRuleLabel4 = new JLabel("<html>3. 난이도는 <font color=#00BFFF'>EASY</font>, <font color='#FFDB58'>NORMAL</font>, <font color='red'>HARD</font>가 있어.</html>",JLabel.CENTER);  //게임방법 라벨 4
		JLabel gameRuleLabel5 = new JLabel("4. 멋진 리듬을 보여주고 랭킹에 올라가보자!",JLabel.CENTER);  //게임방법 라벨 5
		
		JButton OKButton = new JButton("알겠어!",new ImageIcon(imagePath+"Button.png"));  //확인 버튼
		
		Font font = new Font("TDTDTadakTadak",Font.PLAIN,200);  //폰트
		Font font2 = new Font("TDTDTadakTadak",Font.PLAIN,60);  // 조금 작은 폰트
		Font textFont = new Font("TDTDTadakTadak",Font.PLAIN,70);  //게임방법 설명 폰트
		
		/*set*/
		gameRulePanel.setLayout(null);
		gameRuleScreenLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);  //게임방법 화면 라벨
		//게임방법! 텍스트 라벨
		gameRuleText.setFont(font);
		gameRuleText.setBounds(0, -360, FRAME_WIDTH, FRAME_HEIGHT);
		gameRuleText.setHorizontalAlignment(JLabel.CENTER);
		//로고 라벨
		logoTextLabel1.setFont(font2);
		logoTextLabel1.setBounds(110, 20, 200, 100);
		logoTextLabel2.setFont(font2);
		logoTextLabel2.setBounds(110, 68, 200, 100);
		logoTextLabel3.setFont(font2);
		logoTextLabel3.setBounds(110, 116, 200, 100);
		//게임방법 설명 텍스트 라벨
		gameRuleLabel1.setFont(textFont);
		gameRuleLabel1.setBounds(0, 200, 1400, 200);
		
		gameRuleLabel2.setFont(textFont);
		gameRuleLabel2.setBounds(0, 280, 1400, 200);
		
		gameRuleLabel3.setFont(textFont);
		gameRuleLabel3.setBounds(0, 370, 1400, 200);
		
		gameRuleLabel4.setFont(textFont);
		gameRuleLabel4.setBounds(0, 450, 1400, 200);
		
		gameRuleLabel5.setFont(textFont);
		gameRuleLabel5.setBounds(0, 540, 1400, 200);
		//확인버튼
		OKButton.setFont(buttonFont);
		OKButton.setBounds(540,730,320,75);
		OKButton.setHorizontalTextPosition(JButton.CENTER);
		OKButton.setRolloverIcon(clickButtonImg);  //호버링시 이미지 변경
		transparencyButton(OKButton);  //버튼 투명하게
		
		/*add*/
		gameRulePanel.add(logoTextLabel1);  //로고 텍스트 라벨1
		gameRulePanel.add(logoTextLabel2);  //로고 텍스트 라벨2
		gameRulePanel.add(logoTextLabel3);  //로고 텍스트 라벨3
		gameRulePanel.add(gameRuleText);  //게임방법! 텍스트 라벨
		gameRulePanel.add(gameRuleLabel1);  //게임방법 설명 텍스트 라벨1
		gameRulePanel.add(gameRuleLabel2);  //게임방법 설명 텍스트 라벨2
		gameRulePanel.add(gameRuleLabel3);  //게임방법 설명 텍스트 라벨3
		gameRulePanel.add(gameRuleLabel4);  //게임방법 설명 텍스트 라벨4
		gameRulePanel.add(gameRuleLabel5);  //게임방법 설명 텍스트 라벨5
		OKButton.addActionListener(buttonListener);  //액션리스너
		gameRulePanel.add(OKButton);  //확인 버튼
		
		gameRulePanel.add(gameRuleScreenLabel);  //게임방법 화면 라벨
		add(gameRulePanel);
		gameRulePanel.setVisible(false);
	}
}
