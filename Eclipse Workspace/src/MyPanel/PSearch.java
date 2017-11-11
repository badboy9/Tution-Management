package MyPanel;

import Query.SearchDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PSearch extends JDialog implements ActionListener,FocusListener,ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CheckboxGroup cgres,cgs;
	public JTextField tsid,tpid;
	public JButton bhome,bsrch;
	public Checkbox csid,cpid;
	JLabel ls; 
	JLabel []lnm;
	JLabel []lamt;
	JLabel []lsid;
	JLabel []lpid;
	Checkbox cb[];

	Font fobj1,fobj2;
	SearchDB srch;
	JFrame parent;

	public PSearch(JFrame prnt,String title,Boolean bm){
		super(prnt,title,bm);

		parent=prnt;
		srch=new SearchDB();

		fobj1=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,20);
		
		cgres=new CheckboxGroup();
		cgs=new CheckboxGroup();
		ls=new JLabel("Search Payment slip");
		tsid=new JTextField();tsid.setEditable(false);tsid.setFont(fobj2);
		tpid=new JTextField();tpid.setEditable(false);tpid.setFont(fobj2);
		csid=new Checkbox("Student ID",cgs,false);csid.addItemListener(this);csid.setFont(fobj1);
		cpid=new Checkbox("Payment ID",cgs,false);cpid.addItemListener(this);cpid.setFont(fobj1);

		cb=new Checkbox[11];
		lnm=new JLabel[11];
		lsid=new JLabel[11];
		lpid=new JLabel[11];
		lamt=new JLabel[11];

		lnm[0]=new JLabel("Name");lnm[0].setFont(fobj1);
		lsid[0]=new JLabel("Student ID");lsid[0].setFont(fobj1);
		lpid[0]=new JLabel("Payment ID");lpid[0].setFont(fobj1);
		lamt[0]=new JLabel("Amount");lamt[0].setFont(fobj1);

		bhome=new JButton("Home");bhome.addActionListener(this);bhome.setFont(fobj2);
		bsrch=new JButton("Search");bsrch.addActionListener(this);bsrch.setFont(fobj2);
		for(int i=1;i<11;i++){
			lnm[i]=new JLabel();add(lnm[i]);lnm[i].setFont(fobj2);
			lsid[i]=new JLabel();add(lsid[i]);lsid[i].setFont(fobj2);
			lpid[i]=new JLabel();add(lpid[i]);lpid[i].setFont(fobj2);
			lamt[i]=new JLabel();add(lamt[i]);lamt[i].setFont(fobj2);

			cb[i]=new Checkbox(""+i,cgres,false);cb[i].setFont(fobj2);
		}
		add(lnm[0]);add(lamt[0]);add(lsid[0]);add(lpid[0]);
		add(ls);
		add(csid);add(cpid);add(tsid);;add(tpid);
		add(bhome);add(bsrch);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				parent.setVisible(true);
				dispose();	
			}
		});

		setLayout(null);

		ls.setBounds(200,10,400,80);bhome.setBounds(620,20,120,60);
		csid.setBounds(20,100,180,40);tsid.setBounds(200,100,100,40);
		cpid.setBounds(20,150,180,40);tpid.setBounds(200,150,200,40);
		bsrch.setBounds(300,190,200,40);
		lpid[0].setBounds(100,230,100,40);lsid[0].setBounds(200,230,100,40);lnm[0].setBounds(300,230,350,40);lamt[0].setBounds(650,230,200,40);

		for(int i=1;i<11;i++){
			int j=230+(40*i);
			cb[i].setBounds(50,j,50,40);lpid[i].setBounds(100,j,100,40);lsid[i].setBounds(200,j,100,40);lnm[i].setBounds(300,j,350,40);lamt[i].setBounds(650,j,200,40);
		}

		setSize(800,800);
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void itemStateChanged(ItemEvent e){
		clear();
		Checkbox c=(Checkbox)e.getSource();
		if(c==csid){
			tpid.setEditable(false);
			tsid.setEditable(true);
		}
		if(c==cpid){
			tpid.setEditable(true);
			tsid.setEditable(false);

		}
	} 

	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==bhome){
			parent.setVisible(true);
			setVisible(false);
		}
		if(b==bsrch){
			clearLabel();
			Checkbox cb=cgs.getSelectedCheckbox();
			if(cb==csid){
				if(tsid.getText().trim().isEmpty()){
					tsid.requestFocus();
				}
				try{
					int a=Integer.parseInt(tsid.getText());
					ResultSet rs=srch.sdSearch(a);
					fillLabel(rs);
				}
				catch(Exception e1){System.out.println(""+e1);}
				
			}
			if(cb==cpid){
				if(tpid.getText().trim().isEmpty()){
					tpid.requestFocus();
				}
				try{
					int a=Integer.parseInt(tpid.getText()); 
					ResultSet rs=srch.pdSearch(a);
					fillLabel(rs);
				}
				catch(Exception e1){}
				
			}
		}

	}
	public  void focusGained(FocusEvent e){

	}
	public  void focusLost(FocusEvent e){

	}
	void fillLabel(ResultSet rs)throws Exception{
		int i=1;
		while(rs.next()){																//indicates that result found
			int a=rs.getInt(2);
			lpid[i].setText(""+rs.getInt(1));
			lsid[i].setText(""+a);
			ResultSet res=srch.sidSearch(a);
			res.next();
			lnm[i].setText(""+res.getString(2)+" "+res.getString(3)+" "+res.getString(4));
			lamt[i].setText(""+rs.getInt(4));
			//add(cres[i]);
			i++;
		}
	}
	void clearLabel(){
		for(int i=1;i<11;i++){
			lsid[i].setText(null);
			lpid[i].setText(null);
			lamt[i].setText(null);
			lnm[i].setText(null);
			remove(cb[i]);
		}
	}
	void clear(){
		tpid.setText(null);
		tsid.setText(null);
	}
}