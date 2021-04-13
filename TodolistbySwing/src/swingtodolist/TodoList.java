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

	// 내부클래스 객체 생성 전역선언
	// MenuMain menuMain = new MenuMain();
	West west = new West();
	ShowToDoTable showTodo = new ShowToDoTable();
	AddDialog dial;
	// Buttons buttons = new Buttons();

	int updateRow; // 수정 버튼에서 이벤트 발생시.

	// 생성자 외부클래스
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

	// 내부클래스 데이터 입력, 신상정보담당 클래스
	class West extends JPanel {

		// 입력받는 클래스와 신상정보담당 하는 클래스를 만든다.

		Subject sub = new Subject();
		ListOfTodo listof = new ListOfTodo();

		public West() {
			setLayout(new BorderLayout());
			add(sub, BorderLayout.CENTER);
			add(listof, BorderLayout.SOUTH);
		}// endwest

		// 수강 과목목록
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
					la = new JLabel("수강 과목 목록");
					la.setHorizontalAlignment(JLabel.CENTER);
					setLayout(new GridLayout(1, 3, 5, 0));
					addBtn = new JButton("추가");
					delBtn = new JButton("삭제");
					add(la); add(addBtn); add(delBtn);
					addBtn.addActionListener(this);
					delBtn.addActionListener(this);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getActionCommand().equals("추가"))
						//addData(); // 사용자 정의 호출
						dial=new AddDialog();
						dial.setVisible(true);
					if (e.getActionCommand().equals("삭제"))
						deleteData();// 사용자 정의 호출
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
				String[] text = { "과목명", "담당교수", "요일/시간", "수강년도/학기" };
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
				String[] colName = { "과목명", "담당교수", "요일/시간", "수강년도/학기" };

				// 중요
				Vector<Vector<String>> data;
				Vector<String> column_name;

				public ShowTable() {
					data = new Vector<Vector<String>>(10); // 기본10개의 가변배열 생성
					column_name = new Vector<String>(10);

					for (int i = 0; i < colName.length; i++) {
						column_name.add(colName[i]);
					}

					// [중요]
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
				la = new JLabel("TODO LIST 목록");
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
					show_allBtn=new JButton("모든 TODO 출력");
					imp_showBtn=new JButton("중요 TODO 출력");
					add(show_allBtn); add(imp_showBtn);
					setPreferredSize(new Dimension(100, 100));
				}
			}
			
			class LowerButtonMenu extends JPanel{
				public LowerButtonMenu() {
					JButton addBtn, delBtn,modBtn,sortBtn;
					setLayout(new GridLayout(2, 2, 5, 5));
					addBtn = new JButton("추가");
					delBtn = new JButton("삭제");
					modBtn=new JButton("수정");
					sortBtn=new JButton("정렬");
					add(addBtn); add(delBtn);add(modBtn);add(sortBtn);
					setPreferredSize(new Dimension(100, 100));
				}
			}
			
			class ShowTable {
				DefaultTableModel datamodel;
				JTable table;
				JScrollPane scroll;

				// 중요
				Vector<Vector<String>> data;
				Vector<String> column_name;

				public ShowTable() {
					data = new Vector<Vector<String>>(10); // 기본10개의 가변배열 생성
					column_name = new Vector<String>(10);
					column_name.add("해당 과목의 Todo list");
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

		// 셀의 데이타를 가운데 정렬시키기
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cell);
		}

		// 컬럼별버튼 누르면 정렬하기
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

			la = new JLabel("TODO 정보");
			la.setHorizontalAlignment(JLabel.CENTER);
			setLayout(new BorderLayout());
			add(la, BorderLayout.NORTH);
			JTable lists; // todo list 목록 테이블
			Vector<Vector<String>> data = new Vector<Vector<String>>(10); // 기본10개의 가변배열 생성
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
