package swingtodolist;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import swingtodolist.TodoList.West.Subject.AddDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.*;

public class TodoList extends JFrame {

	// ����Ŭ���� ��ü ���� ��������
	// MenuMain menuMain = new MenuMain();
	West west = new West();
	ShowToDoTable showTodo = new ShowToDoTable();
	AddDialog dial;
	// Buttons buttons = new Buttons();

	int updateRow; // ���� ��ư���� �̺�Ʈ �߻���.

	// ������ �ܺ�Ŭ����
	public TodoList() {

		setTitle("Todo List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// add(menuMain.mb, BorderLayout.NORTH);
		add(west, BorderLayout.WEST);
		// add(buttons, BorderLayout.SOUTH);
		add(showTodo.scroll, BorderLayout.CENTER);

		setSize(1000, 800);
		setLocation(500, 50);
		setVisible(true);
	}// end todolist

	// ����Ŭ���� ������ �Է�, �Ż�������� Ŭ����
	class West extends JPanel {

		// �Է¹޴� Ŭ������ �Ż�������� �ϴ� Ŭ������ �����.

		Subject sub = new Subject();
		ListOfTodo listof = new ListOfTodo();

		public West() {
			setLayout(new BorderLayout());
			add(sub, BorderLayout.CENTER);
			add(listof, BorderLayout.SOUTH);
		}// endwest

		// ���� ������
		class Subject extends JPanel {

			ShowTable show = new ShowTable();
			smallMenu sm=new smallMenu();
			public Subject() {
				LineBorder line = new LineBorder(Color.blue, 2);
				setBorder(new TitledBorder(line, ""));

				setLayout(new BorderLayout());
				add(sm, BorderLayout.NORTH);

				
				add(show.scroll, BorderLayout.CENTER);
				setPreferredSize(new Dimension(300, 300));
			}
			
			class smallMenu extends JPanel implements ActionListener{
				public smallMenu() {
					JButton addBtn, delBtn;
					JLabel la;
					la = new JLabel("���� ���� ���");
					la.setHorizontalAlignment(JLabel.CENTER);
					setLayout(new GridLayout(1, 3, 5, 0));
					addBtn = new JButton("�߰�");
					delBtn = new JButton("����");
					add(la); add(addBtn); add(delBtn);
					addBtn.addActionListener(this);
					delBtn.addActionListener(this);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getActionCommand().equals("�߰�"))
						//addData(); // ����� ���� ȣ��
						dial=new AddDialog();
						dial.setVisible(true);
					if (e.getActionCommand().equals("����"))
						deleteData();// ����� ���� ȣ��
				}
				
				/*
				public void addData() {
					
				}
				*/
				public void deleteData() {
					
				}
				
				
			}
			
			class AddDialog extends JDialog{
				JTextField tf[];
				JButton ok=new JButton();
				JButton exit=new JButton();
				String[] text = { "�����", "��米��", "����/�ð�", "�����⵵/�б�" };
				JLabel la;
				
				public AddDialog() {
					setLayout(new GridLayout(5,2,5,5));
					for (int i = 0; i < text.length; i++) {
						la = new JLabel(text[i]);
						tf[i] = new JTextField(20);
						la.setHorizontalAlignment(JLabel.CENTER);
						add(la);
						add(tf[i]);
					}
					add(ok);
					add(exit);
					setSize(200,100);
					
					ok.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							setVisible(false);
						}
					});
					
					exit.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							setVisible(false);
						}
					});
				}
				
			}
			
			class ShowTable {
				DefaultTableModel datamodel;
				JTable table;
				JScrollPane scroll;
				String[] colName = { "�����", "��米��", "����/�ð�", "�����⵵/�б�" };

				// �߿�
				Vector<Vector<String>> data;
				Vector<String> column_name;

				public ShowTable() {
					data = new Vector<Vector<String>>(10); // �⺻10���� �����迭 ����
					column_name = new Vector<String>(10);

					for (int i = 0; i < colName.length; i++) {
						column_name.add(colName[i]);
					}

					// [�߿�]
					datamodel = new DefaultTableModel(data, column_name);
					table = new JTable(datamodel);
					scroll = new JScrollPane(table);
					setTable(table);
				}
			}
			
		}// endsubject

		class ListOfTodo extends JPanel {
			JLabel la;
			ShowTable show = new ShowTable();
			ButtonMenu bm=new ButtonMenu();
			public ListOfTodo() {
				LineBorder line = new LineBorder(Color.blue, 2);
				setBorder(new TitledBorder(line, ""));
				la = new JLabel("TODO LIST ���");
				la.setHorizontalAlignment(JLabel.CENTER);
				setLayout(new BorderLayout());
				add(la, BorderLayout.NORTH);
				add(show.scroll, BorderLayout.CENTER);
				add(bm,BorderLayout.EAST);
				setPreferredSize(new Dimension(300, 400));
			}

			
			class ButtonMenu extends JPanel{
				UpperButtonMenu up=new UpperButtonMenu();
				LowerButtonMenu down=new LowerButtonMenu();
				
				public ButtonMenu() {
					
					setLayout(new BorderLayout(10,150));
					add(up,BorderLayout.NORTH);
					add(down,BorderLayout.CENTER);
					setPreferredSize(new Dimension(130, 300));
				}
						
			}
			
			class UpperButtonMenu extends JPanel{
				public UpperButtonMenu() {
					JButton show_allBtn,imp_showBtn;
					setLayout(new GridLayout(2,1,5,5));
					show_allBtn=new JButton("��� TODO ���");
					imp_showBtn=new JButton("�߿� TODO ���");
					add(show_allBtn); add(imp_showBtn);
					setPreferredSize(new Dimension(100, 100));
				}
			}
			
			class LowerButtonMenu extends JPanel{
				public LowerButtonMenu() {
					JButton addBtn, delBtn,modBtn,sortBtn;
					setLayout(new GridLayout(2, 2, 5, 5));
					addBtn = new JButton("�߰�");
					delBtn = new JButton("����");
					modBtn=new JButton("����");
					sortBtn=new JButton("����");
					add(addBtn); add(delBtn);add(modBtn);add(sortBtn);
					setPreferredSize(new Dimension(100, 100));
				}
			}
			
			class ShowTable {
				DefaultTableModel datamodel;
				JTable table;
				JScrollPane scroll;

				// �߿�
				Vector<Vector<String>> data;
				Vector<String> column_name;

				public ShowTable() {
					data = new Vector<Vector<String>>(10); // �⺻10���� �����迭 ����
					column_name = new Vector<String>(10);
					column_name.add("�ش� ������ Todo list");
					datamodel = new DefaultTableModel(data, column_name);
					table = new JTable(datamodel);
					scroll = new JScrollPane(table);
					setTable(table);
				}
			}

		}// end listoftodo

	}// end west

	public void setTable(JTable table) {
		/*
		 * table.getColumnModel().getColumn(0).setPreferredWidth(50);
		 * table.getColumnModel().getColumn(1).setPreferredWidth(50);
		 * table.getColumnModel().getColumn(2).setPreferredWidth(50);
		 * table.getColumnModel().getColumn(3).setPreferredWidth(50);
		 */
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(50);
		}

		// ���� ����Ÿ�� ��� ���Ľ�Ű��
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cell);
		}

		// �÷�����ư ������ �����ϱ�
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(10);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // SortKey(int column, SortOrder sortOrder)
		sorter.setSortKeys(sortKeys);
	}// end set table

	class ShowToDoTable{

		JLabel la;
		DefaultTableModel datamodel;
		JScrollPane scroll;

		public ShowToDoTable() {

			la = new JLabel("TODO ����");
			la.setHorizontalAlignment(JLabel.CENTER);
			setLayout(new BorderLayout());
			add(la, BorderLayout.NORTH);
			JTable lists; // todo list ��� ���̺�
			Vector<Vector<String>> data = new Vector<Vector<String>>(10); // �⺻10���� �����迭 ����
			Vector<String> column_name = new Vector<String>(10);
			column_name.add(" ");

			datamodel = new DefaultTableModel(data, column_name);
			lists = new JTable(datamodel);
			scroll = new JScrollPane(lists);
			setTable(lists);
		
			// lists.addMouseListener(this);
			//setPreferredSize(new Dimension(250, 300));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TodoList todo = new TodoList();
	}

}
