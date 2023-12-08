import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OgrenciKayit extends JFrame
{
    private JPanel JPanel;
    private JButton saveButton;
    private JTextField textField3;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField4;
    private JComboBox<Ders> comboBox1;

    public OgrenciKayit()
    {
        setTitle("Öğrenci Kayıt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);

        List<Ders> dersListesi = dersler("dersliste.csv");
        comboBox1.setModel(new DefaultComboBoxModel<>(dersListesi.toArray(new Ders[0])));

        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String ad = textField1.getText();
                String soyad = textField2.getText();
                String numara = textField3.getText();
                String bolum = textField4.getText();
                Ders selected = (Ders) comboBox1.getSelectedItem();

                kaydetOgrenciCSV(ad, soyad, numara, bolum, selected);

                JOptionPane.showMessageDialog(null, "Öğrenci bilgileri başarıyla kaydedildi.", "Kayıt", JOptionPane.INFORMATION_MESSAGE);

                Menu menu = new Menu();
                menu.setVisible(true);


                dispose();
            }
        });
    }

    private List<Ders> dersler(String dosyaYolu)
    {
        List<Ders> dersListesi = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dersBilgileri = line.split(",");
                if (dersBilgileri.length == 3) {
                    String dersKodu = dersBilgileri[0].trim();
                    String dersAd = dersBilgileri[1].trim();
                    String dersDonem = dersBilgileri[2].trim();

                    Ders ders = new Ders(dersKodu, dersAd, dersDonem);
                    dersListesi.add(ders);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya kaynaklı bir hata oluştu! " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }

        return dersListesi;
    }

    private void kaydetOgrenciCSV(String ad, String soyad, String numara, String bolum, Ders selected)
    {
        String dosyaYolu = "ogrenciliste.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true)))
        {
            writer.println(String.format("%s,%s,%s,%s,%s", ad, soyad, numara, bolum, selected));
            writer.flush();
        } catch (IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bilgiler kaydedilirken bir hata oluştu! " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new OgrenciKayit();
    }
}
