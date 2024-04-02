import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.BankUser.BankUser;

public class ViewBankUsersView extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;

    public ViewBankUsersView(List<BankUser> users) {
        setTitle("View Bank Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents(users);
        addComponentsToFrame();
        setVisible(true);
    }

    private void initComponents(List<BankUser> users) {
        String[] columns = {"User", "Role ID"};
        Object[][] data = new Object[users.size()][2];

        for (int i = 0; i < users.size(); i++) {
            BankUser user = users.get(i);
            data[i][0] = user.getUserName();
            data[i][1] = user.getRoleID();
        }

        table = new JTable(data, columns);
        scrollPane = new JScrollPane(table);
    }

    private void addComponentsToFrame() {
        add(scrollPane, BorderLayout.CENTER);
    }
}
