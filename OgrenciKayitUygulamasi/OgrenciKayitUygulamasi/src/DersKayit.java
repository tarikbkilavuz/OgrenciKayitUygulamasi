import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DersKayit extends JFrame {

    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton saveButton;

    public DersKayit() {
        initializeComponents();
        setupUI();
    }

    private void initializeComponents() {
        // Form özellikleri
        setTitle("Ders Kayıt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // JPanel'i başlat
        mainPanel = new JPanel();
        setContentPane(mainPanel);

        // Form içeriği
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        saveButton = new JButton("Kaydet");

        mainPanel.add(textField1);
        mainPanel.add(textField2);
        mainPanel.add(textField3);
        mainPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bilgi1 = textField3.getText();
                String bilgi2 = textField1.getText();
                String bilgi3 = textField2.getText();

                kaydetDersCSV(bilgi1, bilgi2, bilgi3);

                JOptionPane.showMessageDialog(null, "Ders bilgileri başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                Menu menu = new Menu();
                menu.setVisible(true);

                dispose();
            }
        });
    }

    private void kaydetDersCSV(String bilgi1, String bilgi2, String bilgi3) {
        String dosyaYolu = "dersliste.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true))) {
            writer.println(bilgi1 + "," + bilgi2 + "," + bilgi3);
            writer.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Dosya yazma hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupUI() {
        // Bu metodun içi boş bırakılabilir.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DersKayit().setVisible(true);
            }
        });
    }
}
