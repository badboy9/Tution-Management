//reading data from database
//adding data to the table and adding table to scroll pane
//adding scrollpane during runtime
//Colouring Alternate Rows

import javax.swing.*;
import Query.SearchDB;
import java.util.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;

class Table extends JFrame
{
	public Table()
	{	
		
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String[] args) {
		Table obj=new Table();		
		obj.design();

		System.out.println("design Fetched");
	}

	void design()
	{
		Border myborder = BorderFactory.createMatteBorder(5, 5, 5, 1, Color.black);
		int i=2;

		JPanel pan=new JPanel();

		JTable tab=new JTable();
		DefaultTableModel dtm=new DefaultTableModel(0,0);

		String[]header =new String[]{"SID","Name","Amount","Contact"};
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);

		tab.setModel(dtm);
		TableColumnModel columnModel = tab.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(120);
		tab.setRowHeight(20);
		
		try
		{
			System.out.println("Fetching Data");
			SearchDB srch=new SearchDB();
			ResultSet rs=srch.retriveAll();
			while(rs.next())
			{
				dtm.addRow(new Object[]{rs.getInt(1)," "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4),rs.getInt(12),rs.getString(5)});
				i++;
			}
		}
		catch(Exception e){System.out.println("Wrong");}

//Code TO Colour Alternate Rows As Gray

tab.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
        return this;
    }
});


		System.out.println("data Fetched");

		pan.add(tab);

		JScrollPane span=new JScrollPane(pan);
		span.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        span.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pan.setPreferredSize(new Dimension(430,450));
		setLayout(null);
		span.setBounds(10,10,450,450);

		add(span);
		pan.revalidate();
        validate();
	}
}

