import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JList;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Tournament {

	private JFrame frmTournamentManager;
	private JTextField textField;
	private JComboBox<Round> roundBox, advBox;
	private JList listParticipants, listTables, listTable;
	private JLabel lblTable, lblPartCnt;
	private int partCount, maxPlayer;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tournament window = new Tournament();
					window.frmTournamentManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tournament() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.partCount = 0;
		this.maxPlayer = 5;
		frmTournamentManager = new JFrame();
		frmTournamentManager.setTitle("Tabletop Tournament");
		frmTournamentManager.setBounds(100, 100, 800, 600);
		frmTournamentManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel sidebar = new JPanel();
		frmTournamentManager.getContentPane().add(sidebar, BorderLayout.WEST);
		GridBagLayout gbl_sidebar = new GridBagLayout();
		gbl_sidebar.columnWidths = new int[] { 97, 0, 0, 0 };
		gbl_sidebar.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_sidebar.columnWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_sidebar.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		sidebar.setLayout(gbl_sidebar);

		roundBox = new JComboBox<Round>();
		roundBox.setToolTipText("Choose round to edit");
		roundBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Tournament.this.repaint();
			}
		});
		GridBagConstraints gbc_roundBox = new GridBagConstraints();
		gbc_roundBox.gridwidth = 2;
		gbc_roundBox.insets = new Insets(0, 0, 5, 5);
		gbc_roundBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_roundBox.gridx = 0;
		gbc_roundBox.gridy = 0;
		sidebar.add(roundBox, gbc_roundBox);

		JButton btnAddRound = new JButton("+");
		btnAddRound.setToolTipText("add Round");
		btnAddRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Neue Runde:");
				Tournament.this.newRound(s);
			}
		});
		GridBagConstraints gbc_btnAddRound = new GridBagConstraints();
		gbc_btnAddRound.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRound.gridx = 2;
		gbc_btnAddRound.gridy = 0;
		sidebar.add(btnAddRound, gbc_btnAddRound);

		listParticipants = new JList();
		GridBagConstraints gbc_listParticipants = new GridBagConstraints();
		gbc_listParticipants.insets = new Insets(0, 0, 5, 0);
		gbc_listParticipants.gridwidth = 3;
		gbc_listParticipants.fill = GridBagConstraints.BOTH;
		gbc_listParticipants.gridx = 0;
		gbc_listParticipants.gridy = 1;
		sidebar.add(listParticipants, gbc_listParticipants);

		textField = new JTextField();
		textField.setToolTipText("Enter name of Participant");
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tournament.this.newParticipant(Tournament.this.textField
						.getText());
			}
		});

		lblPartCnt = new JLabel(" ");
		GridBagConstraints gbc_lblPartCnt = new GridBagConstraints();
		gbc_lblPartCnt.gridwidth = 3;
		gbc_lblPartCnt.insets = new Insets(0, 0, 5, 5);
		gbc_lblPartCnt.gridx = 0;
		gbc_lblPartCnt.gridy = 2;
		sidebar.add(lblPartCnt, gbc_lblPartCnt);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		sidebar.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnAddPart = new JButton("+");
		btnAddPart.setToolTipText("add participant");
		btnAddPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tournament.this.newParticipant(Tournament.this.textField
						.getText());
			}
		});
		GridBagConstraints gbc_btnAddPart = new GridBagConstraints();
		gbc_btnAddPart.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddPart.gridx = 1;
		gbc_btnAddPart.gridy = 3;
		sidebar.add(btnAddPart, gbc_btnAddPart);

		JButton btnDelPart = new JButton("-");
		btnDelPart.setToolTipText("remove selected participant");
		btnDelPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tournament.this.killParticipant();
			}
		});
		GridBagConstraints gbc_btnDelPart = new GridBagConstraints();
		gbc_btnDelPart.gridx = 2;
		gbc_btnDelPart.gridy = 3;
		sidebar.add(btnDelPart, gbc_btnDelPart);

		JPanel mainFrame = new JPanel();
		frmTournamentManager.getContentPane().add(mainFrame,
				BorderLayout.CENTER);
		mainFrame.setLayout(new GridLayout(1, 0, 0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainFrame.add(splitPane);

		JPanel panelTables = new JPanel();
		splitPane.setLeftComponent(panelTables);
		GridBagLayout gbl_panelTables = new GridBagLayout();
		gbl_panelTables.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelTables.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelTables.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelTables.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelTables.setLayout(gbl_panelTables);

		JButton btnSitDown = new JButton("Sit down");
		btnSitDown.setToolTipText("Calculate tables for selected round");
		btnSitDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tournament.this.sitDown();
			}
		});
		GridBagConstraints gbc_btnSitDown = new GridBagConstraints();
		gbc_btnSitDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnSitDown.gridx = 0;
		gbc_btnSitDown.gridy = 0;
		panelTables.add(btnSitDown, gbc_btnSitDown);

		listTables = new JList();
		listTables.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Tournament.this.showTable();
			}
		});

		JLabel lblMaxPlayersPer = new JLabel("max. Players per Table:");
		GridBagConstraints gbc_lblMaxPlayersPer = new GridBagConstraints();
		gbc_lblMaxPlayersPer.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxPlayersPer.gridx = 1;
		gbc_lblMaxPlayersPer.gridy = 0;
		panelTables.add(lblMaxPlayersPer, gbc_lblMaxPlayersPer);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Tournament.this.setMaxPlayer();
			}
		});
		spinner.setModel(new SpinnerNumberModel(new Integer(maxPlayer),
				new Integer(2), null, new Integer(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 0;
		panelTables.add(spinner, gbc_spinner);
		listTables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listTables = new GridBagConstraints();
		gbc_listTables.gridwidth = 4;
		gbc_listTables.fill = GridBagConstraints.BOTH;
		gbc_listTables.gridx = 0;
		gbc_listTables.gridy = 1;
		panelTables.add(listTables, gbc_listTables);

		JPanel panelTable = new JPanel();
		splitPane.setRightComponent(panelTable);
		GridBagLayout gbl_panelTable = new GridBagLayout();
		gbl_panelTable.columnWidths = new int[] { 330, 127, 0, 0 };
		gbl_panelTable.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panelTable.columnWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelTable.rowWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		panelTable.setLayout(gbl_panelTable);

		lblTable = new JLabel("Table");
		GridBagConstraints gbc_lblTable = new GridBagConstraints();
		gbc_lblTable.gridwidth = 3;
		gbc_lblTable.insets = new Insets(0, 0, 5, 0);
		gbc_lblTable.gridx = 0;
		gbc_lblTable.gridy = 0;
		panelTable.add(lblTable, gbc_lblTable);

		listTable = new JList();
		GridBagConstraints gbc_listTable = new GridBagConstraints();
		gbc_listTable.gridwidth = 3;
		gbc_listTable.insets = new Insets(0, 0, 5, 0);
		gbc_listTable.fill = GridBagConstraints.BOTH;
		gbc_listTable.gridx = 0;
		gbc_listTable.gridy = 1;
		panelTable.add(listTable, gbc_listTable);

		this.advBox = new JComboBox<Round>();
		advBox.setToolTipText("choose round to advance");
		GridBagConstraints gbc_advBox = new GridBagConstraints();
		gbc_advBox.insets = new Insets(0, 0, 0, 5);
		gbc_advBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_advBox.gridx = 1;
		gbc_advBox.gridy = 2;
		panelTable.add(advBox, gbc_advBox);

		JButton btnAdvance = new JButton("advance");
		btnAdvance.setToolTipText("add selected participant to selected round");
		btnAdvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tournament.this.advance();
			}
		});
		GridBagConstraints gbc_btnAdvance = new GridBagConstraints();
		gbc_btnAdvance.gridx = 2;
		gbc_btnAdvance.gridy = 2;
		panelTable.add(btnAdvance, gbc_btnAdvance);
	}

	protected void setMaxPlayer() {
		this.maxPlayer = (int) this.spinner.getValue();
	}

	protected void killParticipant() {
		if (this.roundBox.getItemCount() > 0
				&& !this.listParticipants.isSelectionEmpty()) {
			List l = this.listParticipants.getSelectedValuesList();
			Round r = (Round) this.roundBox.getSelectedItem();
			for (Object o : l) {
				r.killParticipant((Participant) o);
				repaint();
			}
		}
	}

	protected void showTable() {
		repaint();
	}

	protected void advance() {
		if (this.advBox.getItemCount() > 0
				&& !this.listTable.isSelectionEmpty()) {
			Round r = (Round) this.advBox.getSelectedItem();
			List ps = this.listTable.getSelectedValuesList();
			for (Object o : ps) {
				Participant p = (Participant) o;
				p.advance(r);
			}
			repaint();
		}

	}

	protected void sitDown() {
		if (this.roundBox.getItemCount() > 0) {
			Round r = (Round) this.roundBox.getSelectedItem();
			r.sitDown(this.maxPlayer);
			repaint();
		}
	}

	public void repaint() {
		if (this.roundBox.getItemCount() > 0) {
			if (!this.listTables.isSelectionEmpty()) {
				Table t = (Table) this.listTables.getSelectedValue();
				this.lblTable.setText("Tisch " + t.getNr());
				this.listTable.setListData(t.getParticipants().toArray());
			}
			Round r = (Round) this.roundBox.getSelectedItem();
			this.listParticipants.setListData(r.getParticipants().toArray());
			this.listTables.setListData(r.getTables().toArray());
			this.lblPartCnt.setText("Number of Participants: "+this.listParticipants.getModel().getSize());
			this.frmTournamentManager.repaint();
		}
	}

	public void newRound(String topic) {
		Round r;
		if (!topic.isEmpty())
			r = new Round(this.roundBox.getItemCount() + 1, topic);
		else
			r = new Round(this.roundBox.getItemCount() + 1);
		this.roundBox.addItem(r);
		this.advBox.addItem(r);
		repaint();
	}

	public void newParticipant(String name) {
		if (this.roundBox.getItemCount() > 0 && !name.isEmpty()) {
			Round r = (Round) this.roundBox.getSelectedItem();
			r.addParticipant(new Participant(name, this.partCount++));
			this.textField.setText("");
			repaint();
		}
	}
}
