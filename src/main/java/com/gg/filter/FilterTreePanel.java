package com.gg.filter;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.TreeNode;

public class FilterTreePanel extends JPanel {

	private static final long	serialVersionUID	= -1896522436719351893L;

	private JTextField			filterField;
	private JTree				tree;
	private FilterTreeModel		model;

	public FilterTreePanel(ComponentFilter filter, TreeNode content) {
		super(new BorderLayout());

		model = new FilterTreeModel(filter, content);
		tree = new JTree(model);
		tree.setCellRenderer(new FilterTreeCellRenderer(model));

		JScrollPane pane = new JScrollPane(tree);
		add(pane);

		filterField = new JTextField(10);
		filterField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
		});
		add(filterField, BorderLayout.NORTH);
		update();
	}

	public JTree getTree() {
		return tree;
	}

	public FilterTreeModel getTreeModel() {
		return model;
	}

	public void update() {
		model.setFilterText(filterField.getText());
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	}
}