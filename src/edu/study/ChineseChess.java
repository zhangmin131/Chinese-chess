/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.study;

/*
 *�й�����Java��V3.0
 *����
 *Դ�ļ�:ChineseChess.java
 *��ӹ���:ʵ���˵�ǰ��ֵı���
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

//����

//�������
public class ChineseChess {
	public static void main(String args[]) {
		new ChessMainFrame("�й�����");
	}
}

class ChessMainFrame extends JFrame implements ActionListener, MouseListener,
		Runnable {
	// ���
	JLabel play[] = new JLabel[32];

	// ����
	JLabel image;

	// ����
	Container con;

	// ������
	JToolBar jmain;
	// ���¿�ʼ
	JButton anew;
	// ����
	JButton repent;
	// ��
	JButton showOpen;
	// ����
	JButton showSave;
	// �˳�
	JButton exit;

	// ��ǰ��Ϣ
	JLabel text;

	// ���浱ǰ����
	Vector FileVar;
	Vector Var;

	// ���������(ʹ�ڵ��÷���)
	ChessRule rule;

	/* ��������********************************* */
	/* chessManClick = true ��˸���� �����߳���Ӧ */
	/* chessManClick = false ������ ֹͣ��˸ �����߳���Ӧ */
	boolean chessManClick;

	/* �����������*************************** */
	/* chessPlayClick=1 �������� */
	/* chessPlayClick=2 �������� Ĭ�Ϻ��� */
	/* chessPlayClick=3 ˫������������ */
	int chessPlayClick = 2;

	// ����������˸���߳�
	Thread tmain;
	// �ѵ�һ�εĵ������Ӹ��߳���Ӧ
	static int Man, i;

	ChessMainFrame() {
	}

	ChessMainFrame(String Title) {
		// ���п͸�����
		con = this.getContentPane();
		con.setLayout(null);
		// ʵ����������
		rule = new ChessRule();
		FileVar = new Vector();
		Var = new Vector();

		// ����������
		jmain = new JToolBar();
		text = new JLabel("  ���һ�ӭ");
		text.setToolTipText("��ʾ��Ϣ");
		anew = new JButton(" �� �� Ϸ ");
		anew.setToolTipText("���¿�ʼ�µ�һ��");
		exit = new JButton(" ��  �� ");
		exit.setToolTipText("�˳�������");
		repent = new JButton(" ��  �� ");
		repent.setToolTipText("���ص��ϴ������λ��");
		showOpen = new JButton("��");
		showOpen.setToolTipText("����ǰ���");
		showSave = new JButton("����");
		showSave.setToolTipText("���浱ǰ���");

		// �������ӵ�������
		jmain.setLayout(new GridLayout(0, 6));
		jmain.add(anew);
		jmain.add(repent);
		jmain.add(showOpen);
		jmain.add(showSave);
		jmain.add(exit);
		jmain.add(text);
		jmain.setBounds(0, 500, 450, 30);
		con.add(jmain);

		// ������ӱ�ǩ
		drawChessMan();

		/* ע������� */

		// ע�ᰴŤ����
		anew.addActionListener(this);
		repent.addActionListener(this);
		exit.addActionListener(this);
		showOpen.addActionListener(this);
		showSave.addActionListener(this);

		// ע�������ƶ�����
		for (int i = 0; i < 32; i++) {
			con.add(play[i]);
			play[i].addMouseListener(this);
		}

		// ������̱�ǩ
		con.add(image = new JLabel(new ImageIcon("img/chess.png")));
		image.setBounds(0, 0, 446, 497);
		image.addMouseListener(this);

		// ע�ᴰ��رռ���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		// �������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();

		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		this.setLocation((screenSize.width - frameSize.width) / 2 - 200,
				(screenSize.height - frameSize.height) / 2 - 290);

		this.setIconImage(new ImageIcon("��1.gif").getImage());
		this.setResizable(false);
		this.setTitle(Title);
		this.setSize(450, 550);
		this.show();
	}

	/* ������ӷ���*********** */
	public void drawChessMan() {
		// ���̿���
		int i, k;
		// ͼ��
		Icon in;

		// ��ɫ����

		// ��
		in = new ImageIcon("img/��2.GIF");
		for (i = 0, k = 10; i < 2; i++, k += 385) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 10, 40, 40);
			play[i].setName("��1");
		}

		// ��
		in = new ImageIcon("img/��2.GIF");
		for (i = 4, k = 60; i < 6; i++, k += 285) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 10, 40, 40);
			play[i].setName("��1");
		}

		// ��
		in = new ImageIcon("img/��2.GIF");
		for (i = 8, k = 105; i < 10; i++, k += 195) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 10, 40, 40);
			play[i].setName("��1");
		}

		// ʿ
		in = new ImageIcon("img/ʿ2.GIF");
		for (i = 12, k = 155; i < 14; i++, k += 95) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 10, 40, 40);
			play[i].setName("ʿ1");
		}

		// ��
		in = new ImageIcon("img/��2.GIF");
		for (i = 16, k = 10; i < 21; i++, k += 96.5) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 160, 40, 40);
			play[i].setName("��1" + i);
		}

		// ��
		in = new ImageIcon("img/��2.GIF");
		for (i = 26, k = 60; i < 28; i++, k += 289) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 110, 40, 40);
			play[i].setName("��1" + i);
		}

		// ��
		in = new ImageIcon("img/˧2.GIF");
		play[30] = new JLabel(in);
		play[30].setBounds(205, 10, 40, 40);
		play[30].setName("��1");

		// ��ɫ����

		// ��
		in = new ImageIcon("img/��1.GIF");
		for (i = 2, k = 10; i < 4; i++, k += 385) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 450, 40, 40);
			play[i].setName("��2");
		}

		// ��
		in = new ImageIcon("img/��1.GIF");
		for (i = 6, k = 60; i < 8; i++, k += 285) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 450, 40, 40);
			play[i].setName("��2");
		}

		// ��
		in = new ImageIcon("img/��1.GIF");
		for (i = 10, k = 105; i < 12; i++, k += 195) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 450, 40, 40);
			play[i].setName("��2");
		}

		// ʿ
		in = new ImageIcon("img/ʿ1.GIF");
		for (i = 14, k = 155; i < 16; i++, k += 95) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 450, 40, 40);
			play[i].setName("ʿ2");
		}

		// ��
		in = new ImageIcon("img/��1.GIF");
		for (i = 21, k = 10; i < 26; i++, k += 96.5) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 300, 40, 40);
			play[i].setName("��2" + i);
		}

		// ��
		in = new ImageIcon("img/��1.GIF");
		for (i = 28, k = 60; i < 30; i++, k += 289) {
			play[i] = new JLabel(in);
			play[i].setBounds(k, 350, 40, 40);
			play[i].setName("��2" + i);
		}

		// ˧
		in = new ImageIcon("img/��1.GIF");
		play[31] = new JLabel(in);
		play[31].setBounds(205, 450, 40, 40);
		play[31].setName("˧2");
	}

	/* �̷߳�������������˸ */
	public void run() {
		while (true) {
			// �������ӵ�һ�¿�ʼ��˸
			if (chessManClick) {
				play[Man].setVisible(false);

				// ʱ�����
				try {
					tmain.sleep(500);
				} catch (Exception e) {
				}

				play[Man].setVisible(true);
			}

			// ��˸��ǰ��ʾ��Ϣ �����û�������
			else {
				text.setVisible(false);

				// ʱ�����
				try {
					tmain.sleep(500);
				} catch (Exception e) {
				}

				text.setVisible(true);
			}

			try {
				tmain.sleep(500);
			} catch (Exception e) {
			}
		}
	}

	/* �������ӷ���*********************** */
	public void mouseClicked(MouseEvent me) {
		// MusicDemo1 t = new MusicDemo1();
		// ��ǰ����
		int Ex = 0, Ey = 0;

		// �����߳�
		if (tmain == null) {
			tmain = new Thread(this);
			tmain.start();
		}

		// ��������(�ƶ�����)
		if (me.getSource().equals(image)) {
			// �ú��������ʱ��
			if (chessPlayClick == 2 && play[Man].getName().charAt(1) == '2') {
				Ex = play[Man].getX();
				Ey = play[Man].getY();

				// �ƶ��䡢��
				if (Man > 15 && Man < 26) {
					rule.armsRule(Man, play[Man], me);
				}

				// �ƶ���
				else if (Man > 25 && Man < 30) {
					rule.cannonRule(play[Man], play, me);
				}

				// �ƶ���
				else if (Man >= 0 && Man < 4) {
					rule.cannonRule(play[Man], play, me);
				}

				// �ƶ���
				else if (Man > 3 && Man < 8) {
					rule.horseRule(play[Man], play, me);
				}

				// �ƶ��ࡢ��
				else if (Man > 7 && Man < 12) {
					rule.elephantRule(Man, play[Man], play, me);
				}

				// �ƶ��ˡ�ʿ
				else if (Man > 11 && Man < 16) {
					rule.chapRule(Man, play[Man], play, me);
				}

				// �ƶ�����˧
				else if (Man == 30 || Man == 31) {
					rule.willRule(Man, play[Man], play, me);
				}

				// �Ƿ��������(�Ƿ���ԭ��û�ж�)
				if (Ex == play[Man].getX() && Ey == play[Man].getY()) {
					text.setText("  ��������");
					chessPlayClick = 2;
				}

				else {
					text.setText("  ��������");
					chessPlayClick = 1;
				}

			}// if

			// �ú��������ʱ��
			else if (chessPlayClick == 1
					&& play[Man].getName().charAt(1) == '1') {
				Ex = play[Man].getX();
				Ey = play[Man].getY();

				// �ƶ��䡢��
				if (Man > 15 && Man < 26) {
					rule.armsRule(Man, play[Man], me);
				}

				// �ƶ���
				else if (Man > 25 && Man < 30) {
					rule.cannonRule(play[Man], play, me);
				}

				// �ƶ���
				else if (Man >= 0 && Man < 4) {
					rule.cannonRule(play[Man], play, me);
				}

				// �ƶ���
				else if (Man > 3 && Man < 8) {
					rule.horseRule(play[Man], play, me);
				}

				// �ƶ��ࡢ��
				else if (Man > 7 && Man < 12) {
					rule.elephantRule(Man, play[Man], play, me);
				}

				// �ƶ��ˡ�ʿ
				else if (Man > 11 && Man < 16) {
					rule.chapRule(Man, play[Man], play, me);
				}

				// �ƶ�����˧
				else if (Man == 30 || Man == 31) {
					rule.willRule(Man, play[Man], play, me);
				}

				// �Ƿ��������(�Ƿ���ԭ��û�ж�)
				if (Ex == play[Man].getX() && Ey == play[Man].getY()) {
					text.setText("  ��������");
					chessPlayClick = 1;
				}

				else {

					text.setText("  ��������");
					chessPlayClick = 2;
				}

			}// else if

			// ��ǰû�в���(ֹͣ��˸)
			chessManClick = false;

		}// if

		// ��������
		else {
			// ��һ�ε�������(��˸����)
			if (!chessManClick) {
				for (int i = 0; i < 32; i++) {
					// ������������
					if (me.getSource().equals(play[i])) {
						// �����߳��ø�������˸
						Man = i;
						// ��ʼ��˸
						chessManClick = true;
						break;
					}
				}// for
			}// if

			// �ڶ��ε�������(������)
			else if (chessManClick) {
				// ��ǰû�в���(ֹͣ��˸)
				chessManClick = false;

				for (i = 0; i < 32; i++) {
					// �ҵ����Ե�����
					if (me.getSource().equals(play[i])) {
						// �ú�������ʱ��
						if (chessPlayClick == 2
								&& play[Man].getName().charAt(1) == '2') {
							Ex = play[Man].getX();
							Ey = play[Man].getY();

							// �䡢���Թ���
							if (Man > 15 && Man < 26) {
								rule.armsRule(play[Man], play[i]);
							}

							// �ڳԹ���
							else if (Man > 25 && Man < 30) {
								rule.cannonRule(0, play[Man], play[i], play, me);
							}

							// ���Թ���
							else if (Man >= 0 && Man < 4) {
								rule.cannonRule(1, play[Man], play[i], play, me);
							}

							// ��Թ���
							else if (Man > 3 && Man < 8) {
								rule.horseRule(play[Man], play[i], play, me);
							}

							// �ࡢ��Թ���
							else if (Man > 7 && Man < 12) {
								rule.elephantRule(play[Man], play[i], play);
							}

							// ʿ���˳������
							else if (Man > 11 && Man < 16) {
								rule.chapRule(Man, play[Man], play[i], play);
							}

							// ����˧�������
							else if (Man == 30 || Man == 31) {
								rule.willRule(Man, play[Man], play[i], play);
								play[Man].setVisible(true);
							}

							// �Ƿ��������(�Ƿ���ԭ��û�ж�)
							if (Ex == play[Man].getX()
									&& Ey == play[Man].getY()) {
								text.setText("  ��������");
								chessPlayClick = 2;
								break;
							}

							else {
								text.setText("  ��������");
								chessPlayClick = 1;
								break;
							}

						}// if

						// �ú�������ʱ��
						else if (chessPlayClick == 1
								&& play[Man].getName().charAt(1) == '1') {
							Ex = play[Man].getX();
							Ey = play[Man].getY();

							// �䡢���Թ���
							if (Man > 15 && Man < 26) {
								rule.armsRule(play[Man], play[i]);
							}

							// �ڳԹ���
							else if (Man > 25 && Man < 30) {
								rule.cannonRule(0, play[Man], play[i], play, me);
							}

							// ���Թ���
							else if (Man >= 0 && Man < 4) {
								rule.cannonRule(1, play[Man], play[i], play, me);
							}

							// ��Թ���
							else if (Man > 3 && Man < 8) {
								rule.horseRule(play[Man], play[i], play, me);
							}

							// �ࡢ��Թ���
							else if (Man > 7 && Man < 12) {
								rule.elephantRule(play[Man], play[i], play);
							}

							// ʿ���˳������
							else if (Man > 11 && Man < 16) {
								rule.chapRule(Man, play[Man], play[i], play);
							}

							// ����˧�������
							else if (Man == 30 || Man == 31) {
								rule.willRule(Man, play[Man], play[i], play);
								play[Man].setVisible(true);
							}

							// �Ƿ��������(�Ƿ���ԭ��û�ж�)
							if (Ex == play[Man].getX()
									&& Ey == play[Man].getY()) {
								text.setText("  ��������");
								chessPlayClick = 1;
								break;
							}

							else {
								text.setText("  ��������");
								chessPlayClick = 2;
								break;
							}

						}// else if

					}// if

				}// for

				// �Ƿ�ʤ��
				if (!play[31].isVisible()) {
					JOptionPane.showConfirmDialog(this, "��ϲ�ڷ���ʤ", "���һʤ��",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE);
					// ˫������������������
					chessPlayClick = 3;
					text.setText("  ��ϲ�ڷ���ʤ");

				}// if

				else if (!play[30].isVisible()) {
					JOptionPane.showConfirmDialog(this, "��ϲ�췽��ʤ", "��Ҷ�ʤ��",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE);
					chessPlayClick = 3;
					text.setText("  ��ϲ�췽��ʤ");
				}// else if

			}// else

		}// else

	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	/* ������Ť******************************** */
	public void actionPerformed(ActionEvent ae) {
		// ���¿�ʼ
		if (ae.getSource().equals(anew)) {
			int i, k;

			// ��������ÿ�����ӵ�λ��
			// ��
			for (i = 0, k = 10; i < 2; i++, k += 385) {
				play[i].setBounds(k, 10, 40, 40);
			}

			// ��
			for (i = 4, k = 60; i < 6; i++, k += 285) {
				play[i].setBounds(k, 10, 40, 40);
			}

			// ��

			for (i = 8, k = 105; i < 10; i++, k += 195) {
				play[i].setBounds(k, 10, 40, 40);
			}

			// ʿ
			for (i = 12, k = 155; i < 14; i++, k += 95) {
				play[i].setBounds(k, 10, 40, 40);
			}

			// ��
			for (i = 16, k = 10; i < 21; i++, k += 96.5) {
				play[i].setBounds(k, 160, 40, 40);
			}

			// ��
			for (i = 26, k = 60; i < 28; i++, k += 289) {
				play[i].setBounds(k, 110, 40, 40);
			}

			// ��
			play[30].setBounds(205, 10, 40, 40);

			// ��ɫ����

			// ��

			for (i = 2, k = 10; i < 4; i++, k += 385) {
				play[i].setBounds(k, 450, 40, 40);
			}

			// ��
			for (i = 6, k = 60; i < 8; i++, k += 285) {
				play[i].setBounds(k, 450, 40, 40);
			}

			// ��
			for (i = 10, k = 105; i < 12; i++, k += 195) {
				play[i].setBounds(k, 450, 40, 40);
			}

			// ʿ
			for (i = 14, k = 155; i < 16; i++, k += 95) {
				play[i].setBounds(k, 450, 40, 40);
			}

			// ��
			for (i = 21, k = 10; i < 26; i++, k += 96.5) {
				play[i].setBounds(k, 300, 40, 40);
			}

			// ��
			for (i = 28, k = 60; i < 30; i++, k += 289) {
				play[i].setBounds(k, 350, 40, 40);
			}

			// ˧
			play[31].setBounds(205, 450, 40, 40);

			chessPlayClick = 2;
			text.setText("  ��������");

			for (i = 0; i < 32; i++) {
				play[i].setVisible(true);
			}

			for (i = 0; i < Var.size(); i++) {
				Var.remove(i);
			}
		}

		// ����
		else if (ae.getSource().equals(repent)) {
			try {
				// ���setVisible����ֵ
				String S = (String) Var.get(Var.size() - 4);
				// ���X����
				int x = Integer.parseInt((String) Var.get(Var.size() - 3));
				// ���Y����
				int y = Integer.parseInt((String) Var.get(Var.size() - 2));
				// �������
				int M = Integer.parseInt((String) Var.get(Var.size() - 1));

				// ��������
				play[M].setVisible(true);
				play[M].setBounds(x, y, 40, 40);

				if (play[M].getName().charAt(1) == '1') {
					text.setText("  ��������");
					chessPlayClick = 1;
				} else {
					text.setText("  ��������");
					chessPlayClick = 2;
				}

				// ɾ���ù�������
				Var.remove(Var.size() - 4);
				Var.remove(Var.size() - 3);
				Var.remove(Var.size() - 2);
				Var.remove(Var.size() - 1);

				// ֹͣ������˸
				chessManClick = false;
			}

			catch (Exception e) {
			}
		}

		// �����
		else if (ae.getSource().equals(showOpen)) {
			try {
				// �򿪶Ի���
				JFileChooser jfcOpen = new JFileChooser("�����");
				int v = jfcOpen.showOpenDialog(this);

				if (v != JFileChooser.CANCEL_OPTION) {
					// ɾ������������Ϣ
					Var.removeAllElements();
					FileVar.removeAllElements();

					// ���ļ������������
					FileInputStream fileIn = new FileInputStream(
							jfcOpen.getSelectedFile());
					ObjectInputStream objIn = new ObjectInputStream(fileIn);
					FileVar = (Vector) objIn.readObject();
					fileIn.close();
					objIn.close();

					// �������ݶ�Ӧ��������
					int k = 0;
					for (int i = 0; i < 32; i++) {
						play[i].setBounds(
								((Integer) FileVar.get(k)).intValue(),
								((Integer) FileVar.get(k + 1)).intValue(), 40,
								40);
						// ���Ե������Ӳ���ʾ
						if (!((Boolean) FileVar.elementAt(k + 2))
								.booleanValue()) {
							play[i].setVisible(false);
						}
						k += 3;
					}

					// ��ǰ���ķ���������
					if (((String) FileVar.lastElement()).toString().equals(
							"  ��������")) {
						text.setText(((String) FileVar.lastElement())
								.toString());
						chessPlayClick = 2;
					} else if (((String) FileVar.lastElement()).toString()
							.equals("  ��������")) {
						text.setText(((String) FileVar.lastElement())
								.toString());
						chessPlayClick = 1;
					} else if (((String) FileVar.lastElement()).toString()
							.substring(5).equals("��")) {
						text.setText(((String) FileVar.lastElement())
								.toString());
						chessPlayClick = 3;
					}

				}
			}

			catch (Exception e) {
				System.out.println("ERROR ShowOpen");
			}
		}

		// ���浱ǰ���
		else if (ae.getSource().equals(showSave)) {
			try {
				// ����Ի���
				JFileChooser jfcSave = new JFileChooser("���浱ǰ���");
				int v = jfcSave.showSaveDialog(this);

				if (v != JFileChooser.CANCEL_OPTION) {
					FileVar.removeAllElements();

					// �����������ӵ�������Ƿ�ɼ�
					for (int i = 0; i < 32; i++) {
						FileVar.addElement(new Integer(play[i].getX()));
						FileVar.addElement(new Integer(play[i].getY()));
						FileVar.addElement(new Boolean(play[i].isVisible()));
					}
					// ���浱ǰ���ķ�����
					FileVar.add(text.getText());

					// ���浽�ļ�
					FileOutputStream fileOut = new FileOutputStream(
							jfcSave.getSelectedFile());
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
					objOut.writeObject(FileVar);
					objOut.close();
					fileOut.close();
				}
			}

			catch (Exception e) {
				System.out.println("ERROR ShowSave");
			}
		}

		// �˳�
		else if (ae.getSource().equals(exit)) {
			int j = JOptionPane.showConfirmDialog(this, "���Ҫ�˳���?", "�˳�",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (j == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	/* ������ */
	class ChessRule {
		/* ���ƶ�����***************************************** */
		public void armsRule(int Man, JLabel play, MouseEvent me) {
			// ��������
			if (Man < 21) {
				// �����ƶ����õ��յ������ģ���ɺϷ�������
				if ((me.getY() - play.getY()) > 40
						&& (me.getY() - play.getY()) < 80
						&& (me.getX() - play.getX()) < 30
						&& (me.getX() - play.getX()) > 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX(), play.getY() + 45, 40, 40);
				}

				// �����ƶ����õ��յ������ģ���ɺϷ������ꡢ�������
				else if (play.getY() >= 250 && (me.getX() - play.getX()) >= 30
						&& (me.getX() - play.getX()) <= 90) {
					play.setBounds(play.getX() + 48, play.getY(), 40, 40);
				}

				// �����ƶ����õ��յ������ģ���ɺϷ������ꡢ�������
				else if (play.getY() >= 250 && (play.getX() - me.getX()) >= 20
						&& (play.getX() - me.getX()) <= 90) {
					// ģ������
					play.setBounds(play.getX() - 48, play.getY(), 40, 40);
				}
			}

			// ��������
			else {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// �����ƶ����õ��յ������ģ���ɺϷ�������
				if ((me.getX() - play.getX()) > 0
						&& (me.getX() - play.getX()) < 30
						&& (play.getY() - me.getY()) > 20
						&& play.getY() - me.getY() < 70) {
					play.setBounds(play.getX(), play.getY() - 48, 40, 40);
				}

				// �����ƶ����õ��յ������ģ���ɺϷ������ꡢ�������
				else if (play.getY() <= 210 && (me.getX() - play.getX()) >= 30
						&& (me.getX() - play.getX()) <= 90) {
					play.setBounds(play.getX() + 50, play.getY(), 40, 40);
				}

				// �����ƶ����õ��յ������ģ���ɺϷ������ꡢ�������
				else if (play.getY() <= 210 && (play.getX() - me.getX()) >= 20
						&& (play.getX() - me.getX()) <= 60) {
					play.setBounds(play.getX() - 52, play.getY(), 40, 40);
				}
			}
		}// ���ƶ�����

		/* ��������********************************* */
		public void armsRule(JLabel play1, JLabel play2) {
			// ������
			if ((play2.getX() - play1.getX()) <= 60
					&& (play2.getX() - play1.getX()) >= 40
					&& (play1.getY() - play2.getY()) < 10
					&& (play1.getY() - play2.getY()) > -10 && play2.isVisible()
					&& play1.getName().charAt(1) != play2.getName().charAt(1)) {
				// ����Ҫ���Ӳ����ҳ���
				if (play1.getName().charAt(1) == '1'
						&& play1.getY() >= 250
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {

					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}

				// ����Ҫ���Ӳ����ܳ���
				else if (play1.getName().charAt(1) == '2'
						&& play1.getY() <= 210
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {
					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}
			}

			// ������
			else if ((play1.getX() - play2.getX()) <= 60
					&& (play1.getX() - play2.getX()) >= 40
					&& (play1.getY() - play2.getY()) < 10
					&& (play1.getY() - play2.getY()) > -10 && play2.isVisible()
					&& play1.getName().charAt(1) != play2.getName().charAt(1)) {
				// ����Ҫ���Ӳ��������
				if (play1.getName().charAt(1) == '1'
						&& play1.getY() >= 250
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {
					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}

				// ����Ҫ���Ӳ����ҳ���
				else if (play1.getName().charAt(1) == '2'
						&& play1.getY() <= 210
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {
					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}
			}

			// ������
			else if (play1.getX() - play2.getX() >= -10
					&& play1.getX() - play2.getX() <= 10
					&& play1.getY() - play2.getY() >= -70
					&& play1.getY() - play2.getY() <= 70) {
				// ���岻�����ϳ���
				if (play1.getName().charAt(1) == '1'
						&& play1.getY() < play2.getY()
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {
					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}

				// ���岻�����³���
				else if (play1.getName().charAt(1) == '2'
						&& play1.getY() > play2.getY()
						&& play1.getName().charAt(1) != play2.getName().charAt(
								1)) {
					play2.setVisible(false);
					// �ѶԷ���λ�ø��Լ�
					play1.setBounds(play2.getX(), play2.getY(), 40, 40);
				}
			}

			// ��ǰ��¼��ӵ�����(���ڻ���)
			Var.add(String.valueOf(play1.isVisible()));
			Var.add(String.valueOf(play1.getX()));
			Var.add(String.valueOf(play1.getY()));
			Var.add(String.valueOf(Man));

			// ��ǰ��¼��ӵ�����(���ڻ���)
			Var.add(String.valueOf(play2.isVisible()));
			Var.add(String.valueOf(play2.getX()));
			Var.add(String.valueOf(play2.getY()));
			Var.add(String.valueOf(i));

		}// ��Խ���

		/* �ڡ����ƶ�����*********************************************** */
		public void cannonRule(JLabel play, JLabel playQ[], MouseEvent me) {
			// �����յ�֮���Ƿ�������
			int Count = 0;

			// �ϡ����ƶ�
			if (play.getX() - me.getX() <= 10 && play.getX() - me.getX() >= -30) {
				// ָ������ģ��Y����
				for (int i = 30; i <= 462; i += 48) {
					// �ƶ���Y�����Ƿ���ָ�����������
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						// ���е�����
						for (int j = 0; j < 32; j++) {
							// �ҳ���ͬһ�����ߵ��������ӡ����������Լ�
							if (playQ[j].getX() - play.getX() >= -10
									&& playQ[j].getX() - play.getX() <= 10
									&& playQ[j].getName() != play.getName()
									&& playQ[j].isVisible()) {
								// ����㵽�յ�(������)
								for (int k = play.getY() + 50; k < i; k += 50) {
									// ������㡢С���յ������Ϳ���֪���м��Ƿ�������
									if (playQ[j].getY() < i
											&& playQ[j].getY() > play.getY()) {
										// �м���һ�����ӾͲ����Դ��������߹�ȥ
										Count++;
										break;
									}
								}// for

								// ����㵽�յ�(���ҵ���)
								for (int k = i + 50; k < play.getY(); k += 50) {
									// �������յ������
									if (playQ[j].getY() < play.getY()
											&& playQ[j].getY() > i) {
										Count++;
										break;
									}
								}// for
							}// if
						}// for

						// �����յ�û�����ӾͿ����ƶ���
						if (Count == 0) {
							// ��ǰ��¼��ӵ�����(���ڻ���)
							Var.add(String.valueOf(play.isVisible()));
							Var.add(String.valueOf(play.getX()));
							Var.add(String.valueOf(play.getY()));
							Var.add(String.valueOf(Man));

							play.setBounds(play.getX(), i - 17, 40, 40);
							break;
						}
					}// if
				}// for
			}// if

			// �����ƶ�
			else if (play.getY() - me.getY() >= -35
					&& play.getY() - me.getY() <= 10) {
				// ָ������ģ��X����
				for (int i = 30; i <= 420; i += 48) {
					// �ƶ���X�����Ƿ���ָ�����������
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						// ���е�����
						for (int j = 0; j < 32; j++) {
							// �ҳ���ͬһ�����ߵ��������ӡ����������Լ�
							if (playQ[j].getY() - play.getY() >= -10
									&& playQ[j].getY() - play.getY() <= 10
									&& playQ[j].getName() != play.getName()
									&& playQ[j].isVisible()) {
								// ����㵽�յ�(���ϵ���)
								for (int k = play.getX() + 50; k < i; k += 50) {
									// ������㡢С���յ������Ϳ���֪���м��Ƿ�������
									if (playQ[j].getX() < i
											&& playQ[j].getX() > play.getX()) {
										// �м���һ�����ӾͲ����Դ��������߹�ȥ
										Count++;
										break;
									}
								}// for

								// ����㵽�յ�(���µ���)
								for (int k = i + 50; k < play.getX(); k += 50) {
									// �������յ������
									if (playQ[j].getX() < play.getX()
											&& playQ[j].getX() > i) {
										Count++;
										break;
									}
								}// for
							}// if
						}// for

						// �����յ�û������
						if (Count == 0) {
							// ��ǰ��¼��ӵ�����(���ڻ���)
							Var.add(String.valueOf(play.isVisible()));
							Var.add(String.valueOf(play.getX()));
							Var.add(String.valueOf(play.getY()));
							Var.add(String.valueOf(Man));
							play.setBounds(i - 20, play.getY(), 40, 40);
							break;
						}
					}// if
				}// for
			}// else

		}// �ڡ����ƶ���������

		/*
		 * �ڡ����������***************************************************************
		 * **********
		 */
		public void cannonRule(int Chess, JLabel play, JLabel playTake,
				JLabel playQ[], MouseEvent me) {
			// �����յ�֮���Ƿ�������
			int Count = 0;

			// ���е�����
			for (int j = 0; j < 32; j++) {
				// �ҳ���ͬһ�����ߵ��������ӡ����������Լ�
				if (playQ[j].getX() - play.getX() >= -10
						&& playQ[j].getX() - play.getX() <= 10
						&& playQ[j].getName() != play.getName()
						&& playQ[j].isVisible()) {
					// �Լ�����㱻�Ե����յ�(���ϵ���)
					for (int k = play.getY() + 50; k < playTake.getY(); k += 50) {
						// ������㡢С���յ������Ϳ���֪���м��Ƿ�������
						if (playQ[j].getY() < playTake.getY()
								&& playQ[j].getY() > play.getY()) {
							// ���������յ�����Ӹ���
							Count++;
							break;
						}
					}// for

					// �Լ�����㱻�Ե����յ�(���µ���)
					for (int k = playTake.getY(); k < play.getY(); k += 50) {
						// �������յ������
						if (playQ[j].getY() < play.getY()
								&& playQ[j].getY() > playTake.getY()) {
							Count++;
							break;
						}
					}// for
				}// if

				// �ҳ���ͬһ�����ߵ��������ӡ����������Լ�
				else if (playQ[j].getY() - play.getY() >= -10
						&& playQ[j].getY() - play.getY() <= 10
						&& playQ[j].getName() != play.getName()
						&& playQ[j].isVisible()) {
					// �Լ�����㱻�Ե����յ�(������)
					for (int k = play.getX() + 50; k < playTake.getX(); k += 50) {
						// ������㡢С���յ������Ϳ���֪���м��Ƿ�������
						if (playQ[j].getX() < playTake.getX()
								&& playQ[j].getX() > play.getX()) {
							Count++;
							break;
						}
					}// for

					// �Լ�����㱻�Ե����յ�(���ҵ���)
					for (int k = playTake.getX(); k < play.getX(); k += 50) {
						// �������յ������
						if (playQ[j].getX() < play.getX()
								&& playQ[j].getX() > playTake.getX()) {
							Count++;
							break;
						}
					}// for
				}// if
			}// for

			// �����յ�֮��Ҫһ���������ڵĹ��򡢲����ܳ��Լ�������
			if (Count == 1 && Chess == 0
					&& playTake.getName().charAt(1) != play.getName().charAt(1)) {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}

			// �����յ�֮��û�������ǳ��Ĺ��򡢲����ܳ��Լ�������
			else if (Count == 0 && Chess == 1
					&& playTake.getName().charAt(1) != play.getName().charAt(1)) {

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}

		}// �ڡ������巽������

		/* ���ƶ�����************************************************** */
		public void horseRule(JLabel play, JLabel playQ[], MouseEvent me) {
			// ����������ϰ�
			int Ex = 0, Ey = 0, Move = 0;

			// ���ơ����
			if (play.getX() - me.getX() >= 10 && play.getX() - me.getX() <= 50
					&& play.getY() - me.getY() >= 60
					&& play.getY() - me.getY() <= 100) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					// �ƶ���Y�����Ƿ���ָ�����������
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					// �ƶ���X�����Ƿ���ָ�����������
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// ��ǰ���Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move = 1;
						break;
					}
				}

				// �����ƶ�������
				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}

			}// if

			// ���ơ��ϱ�
			else if (play.getY() - me.getY() >= 10
					&& play.getY() - me.getY() <= 50
					&& play.getX() - me.getX() >= 60
					&& play.getX() - me.getX() <= 100) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// �����Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& play.getX() - playQ[i].getX() >= 40
							&& play.getX() - playQ[i].getX() <= 60) {
						Move = 1;
						break;
					}
				}

				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}
			}// else

			// ���ơ��ұ�
			else if (me.getY() - play.getY() >= 100
					&& me.getY() - play.getY() <= 130
					&& me.getX() - play.getX() <= 70
					&& me.getX() - play.getX() >= 30) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// ���·��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& playQ[i].getY() - play.getY() >= 40
							&& playQ[i].getY() - play.getY() <= 60) {
						Move = 1;
						break;
					}
				}

				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}
			}// else

			// ���ơ��ұ�
			else if (play.getY() - me.getY() >= 60
					&& play.getY() - me.getY() <= 100
					&& me.getX() - play.getX() <= 80
					&& me.getX() - play.getX() >= 50) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// ��ǰ���Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move = 1;
						break;
					}
				}

				// �����ƶ�������
				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}
			}// else

			// ���ơ����
			else if (me.getY() - play.getY() >= 100
					&& me.getY() - play.getY() <= 140
					&& play.getX() - me.getX() <= 50
					&& play.getX() - me.getX() >= 10) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// ���·��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() <= -40
							&& play.getY() - playQ[i].getY() >= -60) {
						Move = 1;
						break;
					}
				}

				// �����ƶ�������
				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}

			}// else

			// ���ơ��ϱ�
			else if (play.getY() - me.getY() >= 10
					&& play.getY() - me.getY() <= 50
					&& me.getX() - play.getX() <= 140
					&& me.getX() - play.getX() >= 100) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// ���ҷ��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 60) {
						Move = 1;
						break;
					}
				}

				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}
			}// else

			// ���ơ��±�
			else if (me.getY() - play.getY() >= 60
					&& me.getY() - play.getY() <= 90
					&& me.getX() - play.getX() <= 130
					&& me.getX() - play.getX() >= 100) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// ���ҷ��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 60) {
						Move = 1;
						break;
					}
				}

				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}
			}// else

			// ���ơ��±�
			else if (me.getY() - play.getY() >= 50
					&& me.getY() - play.getY() <= 90
					&& play.getX() - me.getX() <= 100
					&& play.getX() - me.getX() >= 50) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// �����Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& play.getX() - playQ[i].getX() >= 40
							&& play.getX() - playQ[i].getX() <= 60) {
						Move = 1;
						break;
					}
				}

				if (Move == 0) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 17, 40, 40);
				}

			}// else

		}// ���ƶ�����

		/*
		 * ��������*****************************************************************
		 * **
		 */
		public void horseRule(JLabel play, JLabel playTake, JLabel playQ[],
				MouseEvent me) {
			// �ϰ�
			int Move = 0;
			boolean Chess = false;

			// ���ơ����
			if (play.getName().charAt(1) != playTake.getName().charAt(1)
					&& play.getX() - playTake.getX() >= 10
					&& play.getX() - playTake.getX() <= 55
					&& play.getY() - playTake.getY() >= 60
					&& play.getY() - playTake.getY() <= 105) {
				// ��ǰ���Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// if

			// ���ơ��ҳ�
			else if (play.getY() - playTake.getY() >= 80
					&& play.getY() - playTake.getY() <= 110
					&& playTake.getX() - play.getX() <= 60
					&& playTake.getX() - play.getX() >= 40) {
				// ��ǰ���Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ��ϳ�
			else if (play.getY() - playTake.getY() >= 40
					&& play.getY() - playTake.getY() <= 60
					&& play.getX() - playTake.getX() >= 90
					&& play.getX() - playTake.getX() <= 110) {
				// �����Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& play.getX() - playQ[i].getX() >= 40
							&& play.getX() - playQ[i].getX() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ��³�
			else if (playTake.getY() - play.getY() >= 30
					&& playTake.getY() - play.getY() <= 60
					&& play.getX() - playTake.getX() <= 120
					&& play.getX() - playTake.getX() >= 80) {
				// �����Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& play.getX() - playQ[i].getX() >= 40
							&& play.getX() - playQ[i].getX() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ��ϳ�
			else if (play.getY() - playTake.getY() >= 30
					&& play.getY() - playTake.getY() <= 60
					&& playTake.getX() - play.getX() <= 120
					&& playTake.getX() - play.getX() >= 80) {
				// ���ҷ��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ��³�
			else if (playTake.getY() - play.getY() >= 30
					&& playTake.getY() - play.getY() <= 60
					&& playTake.getX() - play.getX() <= 120
					&& playTake.getX() - play.getX() >= 80) {
				// ���ҷ��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getY() - playQ[i].getY() <= 10
							&& play.getY() - playQ[i].getY() >= -10
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ����
			else if (playTake.getY() - play.getY() >= 80
					&& playTake.getY() - play.getY() <= 120
					&& play.getX() - playTake.getX() <= 60
					&& play.getX() - playTake.getX() >= 30) {
				// ���·��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& play.getY() - playQ[i].getY() <= -40
							&& play.getY() - playQ[i].getY() >= -60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// ���ơ��ҳ�
			else if (playTake.getY() - play.getY() >= 80
					&& playTake.getY() - play.getY() <= 120
					&& playTake.getX() - play.getX() <= 60
					&& playTake.getX() - play.getX() >= 40) {
				// ���·��Ƿ��б������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() <= 10
							&& play.getX() - playQ[i].getX() >= -10
							&& playQ[i].getY() - play.getY() >= 40
							&& playQ[i].getY() - play.getY() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// û���ϰ��������Գ��塢���ܳ��Լ���ɫ
			if (Chess && Move == 0
					&& playTake.getName().charAt(1) != play.getName().charAt(1)) {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}
		}

		/* ���ƶ�����***************************************************** */
		public void elephantRule(int Man, JLabel play, JLabel playQ[],
				MouseEvent me) {
			// ������ϰ�
			int Ex = 0, Ey = 0, Move = 0;

			// ����
			if (play.getX() - me.getX() <= 90 && play.getX() - me.getX() >= 60
					&& play.getY() - me.getY() <= 100
					&& play.getY() - me.getY() >= 70) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// ���Ϸ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() >= 10
							&& play.getX() - playQ[i].getX() <= 50
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move++;
						break;
					}
				}

				// ���첻�ܹ�����
				if (Move == 0 && Ey > 230 && Man > 9) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

				// ���첻�ܹ�����
				else if (Move == 0 && Ey < 270 && Man < 10) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}
			}// if

			// ����
			else if (play.getY() - me.getY() <= 100
					&& play.getY() - me.getY() >= 70
					&& me.getX() - play.getX() >= 100
					&& me.getX() - play.getX() <= 140) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// ���Ϸ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 90
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move++;
						break;
					}
				}

				// �ࡢ�����
				if (Move == 0 && Ey > 230 && Man > 9) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

				else if (Move == 0 && Ey < 270 && Man < 10) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

			}// else if

			// ����
			else if (play.getX() - me.getX() <= 100
					&& play.getX() - me.getX() >= 60
					&& me.getY() - play.getY() <= 130
					&& me.getY() - play.getY() >= 100) {
				// �Ϸ���Y����
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
						break;
					}
				}

				// �Ϸ���X����
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
						break;
					}
				}

				// �����Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() >= 10
							&& play.getX() - playQ[i].getX() <= 60
							&& play.getY() - playQ[i].getY() <= -40
							&& play.getY() - playQ[i].getY() >= -60) {
						Move++;
						break;
					}
				}

				// �ࡢ�����

				if (Move == 0 && Ey > 230 && Man > 9) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

				else if (Move == 0 && Ey < 270 && Man < 10) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}
			}// else if

			// ����
			else if (me.getX() - play.getX() >= 100
					&& me.getX() - play.getX() <= 130
					&& me.getY() - play.getY() >= 100
					&& me.getY() - play.getY() <= 130) {
				// Y
				for (int i = 30; i <= 462; i += 48) {
					if (i - me.getY() >= -10 && i - me.getY() <= 30) {
						Ey = i;
					}
				}

				// X
				for (int i = 30; i <= 420; i += 48) {
					if (i - me.getX() >= -35 && i - me.getX() <= 10) {
						Ex = i;
					}
				}

				// ���ҷ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& playQ[i].getX() - play.getX() >= 50
							&& playQ[i].getX() - play.getX() <= 80
							&& playQ[i].getY() - play.getY() >= 40
							&& playQ[i].getY() - play.getY() <= 60) {
						Move = 1;
						break;
					}
				}

				// �ࡢ�����
				if (Move == 0 && Ey > 230 && Man > 9) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

				else if (Move == 0 && Ey < 270 && Man < 10) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(Ex - 20, Ey - 15, 40, 40);
				}

			}// else

		}// ���ƶ�������

		/* �ࡢ��������************************************************** */
		public void elephantRule(JLabel play, JLabel playTake, JLabel playQ[]) {
			// �ϰ�
			int Move = 0;
			boolean Chess = false;

			// �����Ϸ�������
			if (play.getX() - playTake.getX() >= 80
					&& play.getX() - playTake.getX() <= 100
					&& play.getY() - playTake.getY() >= 80
					&& play.getY() - playTake.getY() <= 110) {
				// ���Ϸ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() >= 10
							&& play.getX() - playQ[i].getX() <= 50
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move++;
						break;
					}
				}// for

				Chess = true;

			}// if

			// �����Ϸ�������
			else if (playTake.getX() - play.getX() >= 110
					&& playTake.getX() - play.getX() <= 80
					&& play.getY() - playTake.getY() >= 80
					&& play.getY() - playTake.getY() <= 110) {
				// ���Ϸ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& playQ[i].getX() - play.getX() >= 40
							&& playQ[i].getX() - play.getX() <= 90
							&& play.getY() - playQ[i].getY() >= 40
							&& play.getY() - playQ[i].getY() <= 60) {
						Move++;
						break;
					}
				}// for

				Chess = true;
			}// else

			// �����󷽵�����
			else if (play.getX() - playTake.getX() >= 80
					&& play.getX() - playTake.getX() <= 110
					&& playTake.getY() - play.getY() >= 80
					&& playTake.getY() - play.getY() <= 110) {
				// �����Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& play.getX() - playQ[i].getX() >= 10
							&& play.getX() - playQ[i].getX() <= 60
							&& play.getY() - playQ[i].getY() <= -40
							&& play.getY() - playQ[i].getY() >= -60) {
						Move++;
						break;
					}
				}// for

				Chess = true;
			}// else

			// �����ҷŵ�����
			else if (playTake.getX() - play.getX() >= 80
					&& playTake.getX() - play.getX() <= 110
					&& playTake.getY() - play.getY() >= 80
					&& playTake.getY() - play.getY() <= 110) {
				// ���ҷ��Ƿ�������
				for (int i = 0; i < 32; i++) {
					if (playQ[i].isVisible()
							&& playQ[i].getX() - play.getX() >= 50
							&& playQ[i].getX() - play.getX() <= 80
							&& playQ[i].getY() - play.getY() >= 40
							&& playQ[i].getY() - play.getY() <= 60) {
						Move = 1;
						break;
					}
				}// for

				Chess = true;

			}// else

			// û���ϰ��������ܳ��Լ�������
			if (Chess && Move == 0
					&& playTake.getName().charAt(1) != play.getName().charAt(1)) {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}

		}// �ࡢ�����������

		/* ʿ�����ƶ�����****************************************************** */
		public void chapRule(int Man, JLabel play, JLabel playQ[], MouseEvent me) {
			// �ϡ���
			if (me.getX() - play.getX() >= 50 && me.getX() - play.getX() <= 80
					&& play.getY() - me.getY() >= 15
					&& play.getY() - me.getY() <= 50) {
				// ʿ���ܳ����Լ��Ľ���
				if (Man < 14 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY() - 50, 40, 40);
				}

				// �˲��ܳ����Լ��Ľ���
				else if (Man > 13 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY() - 50, 40, 40);
				}
			}// else if

			// �ϡ���
			else if (play.getX() - me.getX() <= 50
					&& play.getX() - me.getX() >= 13
					&& play.getY() - me.getY() >= 15
					&& play.getY() - me.getY() <= 50) {
				// ʿ���˹���
				if (Man < 14 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY() - 50, 40, 40);
				}

				else if (Man > 13 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY() - 50, 40, 40);
				}
			}// else if

			// �¡���
			else if (play.getX() - me.getX() <= 50
					&& play.getX() - me.getX() >= 15
					&& me.getY() - play.getY() >= 50
					&& me.getY() - play.getY() <= 80) {
				// ʿ���˹���
				if (Man < 14 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY() + 50, 40, 40);
				}

				else if (Man > 13 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY() + 50, 40, 40);
				}

			}// else if

			// �¡���
			else if (me.getX() - play.getX() >= 50
					&& me.getX() - play.getX() <= 80
					&& me.getY() - play.getY() >= 50
					&& me.getY() - play.getY() <= 80) {
				// ʿ���˹���
				if (Man < 14 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY() + 50, 40, 40);
				}

				else if (Man > 13 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY() + 50, 40, 40);
				}
			}// else if

		}// ʿ�����ƶ��������

		/* ʿ���˳������******************************************************** */
		public void chapRule(int Man, JLabel play, JLabel playTake,
				JLabel playQ[]) {
			// ��ǰ״̬
			boolean Chap = false;

			// �ϡ���
			if (playTake.getX() - play.getX() >= 30
					&& playTake.getX() - play.getX() <= 60
					&& play.getY() - playTake.getY() >= 30
					&& play.getY() - playTake.getY() <= 60) {
				// ���Ե������Ƿ�͵�ǰʿ���
				if (Man < 14 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150 && playTake.isVisible()) {
					Chap = true;
				}

				// ���Ե������Ƿ�͵�ǰ�����
				else if (Man > 13 && playTake.getX() > 150
						&& playTake.getX() < 300 && playTake.getY() > 340
						&& playTake.isVisible()) {
					Chap = true;
				}
			}// if

			// �ϡ���
			else if (play.getX() - playTake.getX() <= 60
					&& play.getX() - playTake.getX() >= 30
					&& play.getY() - playTake.getY() >= 30
					&& play.getY() - playTake.getY() <= 60) {
				// ���Ե������Ƿ�͵�ǰʿ���
				if (Man < 14 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150 && playTake.isVisible()) {
					Chap = true;
				}

				// ���Ե������Ƿ�͵�ǰ�����
				else if (Man > 13 && playTake.getX() > 150
						&& playTake.getX() < 300 && playTake.getY() > 340
						&& playTake.isVisible()) {
					Chap = true;
				}
			}// else if

			// �¡���
			else if (play.getX() - playTake.getX() <= 60
					&& play.getX() - playTake.getX() >= 30
					&& playTake.getY() - play.getY() >= 30
					&& playTake.getY() - play.getY() <= 60) {
				// ���Ե������Ƿ�͵�ǰʿ���
				if (Man < 14 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150 && playTake.isVisible()) {
					Chap = true;
				}

				// ���Ե������Ƿ�͵�ǰ�����
				else if (Man > 13 && playTake.getX() > 150
						&& playTake.getX() < 300 && playTake.getY() > 340
						&& playTake.isVisible()) {
					Chap = true;
				}
			}// else if

			// �¡���
			else if (playTake.getX() - play.getX() >= 30
					&& playTake.getX() - play.getX() <= 60
					&& playTake.getY() - play.getY() >= 30
					&& playTake.getY() - play.getY() <= 60) {
				// ���Ե������Ƿ�͵�ǰʿ���
				if (Man < 14 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150 && playTake.isVisible()) {
					Chap = true;
				}

				// ���Ե������Ƿ�͵�ǰ�����
				else if (Man > 13 && playTake.getX() > 150
						&& playTake.getX() < 300 && playTake.getY() > 340
						&& playTake.isVisible()) {
					Chap = true;
				}
			}// else if

			// ���ƶ��������ܳ��Լ�������
			if (Chap
					&& playTake.getName().charAt(1) != play.getName().charAt(1)) {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}

		}// ʿ���˳���������

		/* ����˧�ƶ�����****************************************************** */
		public void willRule(int Man, JLabel play, JLabel playQ[], MouseEvent me) {
			// ����
			if (me.getX() - play.getX() >= -5 && me.getX() - play.getX() <= 35
					&& play.getY() - me.getY() <= 50
					&& play.getY() - me.getY() >= 15) {
				// ���Ƿ񳬹��Լ��Ľ���
				if (Man == 30 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX(), play.getY() - 50, 40, 40);
				}

				// ˧�Ƿ񳬹��Լ��Ľ���
				else if (Man == 31 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX(), play.getY() - 50, 40, 40);
				}
			}// if

			// ����
			else if (play.getX() - me.getX() >= 15
					&& play.getX() - me.getX() <= 50
					&& me.getY() - play.getY() <= 40
					&& me.getY() - play.getY() >= -5) {
				// ���Ƿ񳬹��Լ��Ľ���
				if (Man == 30 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY(), 40, 40);
				}

				// ˧�Ƿ񳬹��Լ��Ľ���
				else if (Man == 31 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() - 50, play.getY(), 40, 40);
				}
			}// else if

			// ����
			else if (me.getX() - play.getX() >= 50
					&& me.getX() - play.getX() <= 85
					&& me.getY() - play.getY() <= 40
					&& me.getY() - play.getY() >= -5) {
				// ����˧����
				if (Man == 30 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY(), 40, 40);
				}

				else if (Man == 31 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX() + 50, play.getY(), 40, 40);
				}
			}// else if

			// ����
			else if (me.getX() - play.getX() >= -5
					&& me.getX() - play.getX() <= 35
					&& me.getY() - play.getY() <= 85
					&& me.getY() - play.getY() >= 50) {
				// ����˧����
				if (Man == 30 && me.getX() > 150 && me.getX() < 300
						&& me.getY() < 150) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX(), play.getY() + 50, 40, 40);
				}

				else if (Man == 31 && me.getY() > 340 && me.getX() > 150
						&& me.getX() < 300) {
					// ��ǰ��¼��ӵ�����(���ڻ���)
					Var.add(String.valueOf(play.isVisible()));
					Var.add(String.valueOf(play.getX()));
					Var.add(String.valueOf(play.getY()));
					Var.add(String.valueOf(Man));

					play.setBounds(play.getX(), play.getY() + 50, 40, 40);

				}

			}// else if

		}// ����˧�ƶ��������

		public void willRule(int Man, JLabel play, JLabel playTake,
				JLabel playQ[]) {
			// ��ǰ״̬
			boolean will = false;

			// ���ϳ�
			if (play.getX() - playTake.getX() >= -10
					&& play.getX() - playTake.getX() <= 10
					&& play.getY() - playTake.getY() >= 40
					&& play.getY() - playTake.getY() <= 60
					&& playTake.isVisible()) {
				// ���Ե������Ƿ�͵�ǰ�����
				if (Man == 30 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150) {
					will = true;
				}

				// ���Ե������Ƿ�͵�ǰ˧���
				else if (Man == 31 && playTake.getY() > 340
						&& playTake.getX() > 150 && playTake.getX() < 300) {
					will = true;
				}
			}

			// �����
			else if (play.getX() - playTake.getX() >= 40
					&& play.getX() - playTake.getX() <= 60
					&& playTake.getY() - play.getY() <= 10
					&& playTake.getY() - play.getY() >= -10
					&& playTake.isVisible()) {
				// ����˧����
				if (Man == 30 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150) {
					will = true;
				}

				else if (Man == 31 && playTake.getY() > 340
						&& playTake.getX() > 150 && playTake.getX() < 300) {
					will = true;
				}
			}

			// ���ҳ�
			else if (playTake.getX() - play.getX() >= 40
					&& playTake.getX() - play.getX() <= 60
					&& playTake.getY() - play.getY() <= 10
					&& playTake.getY() - play.getY() >= -10
					&& playTake.isVisible()) {
				// ����˧����
				if (Man == 30 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150) {
					will = true;
				}

				else if (Man == 31 && playTake.getY() > 340
						&& playTake.getX() > 150 && playTake.getX() < 300) {
					will = true;
				}
			}

			// ����
			else if (playTake.getX() - play.getX() >= -10
					&& playTake.getX() - play.getX() <= 10
					&& playTake.getY() - play.getY() <= 60
					&& playTake.getY() - play.getY() >= 40
					&& playTake.isVisible()) {
				// ����˧����
				if (Man == 30 && playTake.getX() > 150 && playTake.getX() < 300
						&& playTake.getY() < 150) {
					will = true;
				}

				else if (Man == 31 && playTake.getY() > 340
						&& playTake.getX() > 150 && playTake.getX() < 300) {
					will = true;
				}
			}

			// ���ܳ��Լ������ӡ����ϵ�ǰҪ��
			if (playTake.getName().charAt(1) != play.getName().charAt(1)
					&& will) {
				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(play.isVisible()));
				Var.add(String.valueOf(play.getX()));
				Var.add(String.valueOf(play.getY()));
				Var.add(String.valueOf(Man));

				// ��ǰ��¼��ӵ�����(���ڻ���)
				Var.add(String.valueOf(playTake.isVisible()));
				Var.add(String.valueOf(playTake.getX()));
				Var.add(String.valueOf(playTake.getY()));
				Var.add(String.valueOf(i));

				playTake.setVisible(false);
				play.setBounds(playTake.getX(), playTake.getY(), 40, 40);
			}

		}// ����˧�Թ������

	}// ������

}// �������