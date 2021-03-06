package View;

import Controller.Controle;
import Model.AuxEmprestimoObraBEAN;
import Model.ClientesBEAN;
import Model.EmprestimoBEAN;
import Model.ObrasBEAN;
import Model.UsuariosBEAN;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class RelatorioEmprestimos extends javax.swing.JDialog {
    
    private javax.swing.table.DefaultTableModel modelo1; 
    
    static  Controle controle = new Controle();
    DefaultTableModel modelo;
    private UsuariosBEAN usuario = null;

    public RelatorioEmprestimos(UsuariosBEAN usuario) {
        this.usuario = usuario;
        initComponents();   
        modelo = (DefaultTableModel) tabela_emp.getModel();
        modelo.setNumRows(0);
        cb_data.setEnabled(false);         
    }
    
    private void prencherTabela(ArrayList<EmprestimoBEAN> list){
        
        tabela_emp.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabela_emp.getColumnModel().getColumn(1).setPreferredWidth(700);
        tabela_emp.getColumnModel().getColumn(2).setPreferredWidth(600);
        tabela_emp.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabela_emp.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        modelo.setNumRows(0);
        
        for (EmprestimoBEAN emp : list){
            ClientesBEAN cliente = controle.findClienteCodigo(emp.getCodigoCliente());
            ArrayList<AuxEmprestimoObraBEAN> aux = controle.listaAuxEmprestimo(emp.getCodigoEmprestimo());
            ObrasBEAN obra = null;
            for( AuxEmprestimoObraBEAN au : aux){
                obra = controle.findObraCodigo(au.getCodigoObra());
                
                UsuariosBEAN usuarioAux = controle.findUsuarioCodigo(emp.getCodigoFuncionario());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                modelo.addRow(new Object[]{usuarioAux.getNome(),obra.getTitulo(), 
                    cliente.getNome(), sdf.format(emp.getSaida()), sdf.format(emp.getDevolucao())});
            }       
        }      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEditora = new javax.swing.JLabel();
        cb_data = new com.toedter.calendar.JDateChooser();
        labelTitulo2 = new javax.swing.JLabel();
        radio2 = new javax.swing.JRadioButton();
        botaoPDF = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabela_emp = new javax.swing.JTable();
        botaoBusca1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(930, 510));
        setPreferredSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelEditora.setBackground(new java.awt.Color(0, 0, 0));
        labelEditora.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelEditora.setText("Relatório de empréstimo");
        getContentPane().add(labelEditora, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));
        getContentPane().add(cb_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 110, 30));

        labelTitulo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelTitulo2.setText("Data de devolução");
        getContentPane().add(labelTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

        radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio2ActionPerformed(evt);
            }
        });
        getContentPane().add(radio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 20, -1));

        botaoPDF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/Save_37110 (1).png"))); // NOI18N
        botaoPDF.setText("PDF");
        botaoPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPDFActionPerformed(evt);
            }
        });
        getContentPane().add(botaoPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 140, 30));

        tabela_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Funcionário", "Obra", "Cliente", "D. EMP", "D. DEV"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_emp.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tabela_emp);
        if (tabela_emp.getColumnModel().getColumnCount() > 0) {
            tabela_emp.getColumnModel().getColumn(0).setMinWidth(150);
            tabela_emp.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabela_emp.getColumnModel().getColumn(0).setMaxWidth(150);
            tabela_emp.getColumnModel().getColumn(1).setResizable(false);
            tabela_emp.getColumnModel().getColumn(1).setPreferredWidth(170);
            tabela_emp.getColumnModel().getColumn(2).setMinWidth(170);
            tabela_emp.getColumnModel().getColumn(2).setPreferredWidth(170);
            tabela_emp.getColumnModel().getColumn(2).setMaxWidth(170);
            tabela_emp.getColumnModel().getColumn(3).setMinWidth(150);
            tabela_emp.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabela_emp.getColumnModel().getColumn(3).setMaxWidth(150);
            tabela_emp.getColumnModel().getColumn(4).setMinWidth(150);
            tabela_emp.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabela_emp.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 875, 390));

        botaoBusca1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botaoBusca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/search-good_icon-icons.com_51027.png"))); // NOI18N
        botaoBusca1.setText("Pesquisar");
        botaoBusca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBusca1ActionPerformed(evt);
            }
        });
        getContentPane().add(botaoBusca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 140, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio2ActionPerformed
        if(radio2.isSelected()){
            if(cb_data.isEnabled() == false){

                cb_data.setEnabled(true);
            }

        }
    }//GEN-LAST:event_radio2ActionPerformed

    private void botaoPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPDFActionPerformed
        ArrayList<EmprestimoBEAN> aux = new ArrayList<EmprestimoBEAN>();
        if (cb_data.isEnabled()) {
            Date data = new Date(cb_data.getDate().getTime());
            aux = controle.findEmprestimoData(data);
        }else{
            Date data =  new Date(100);
            aux = controle.findEmprestimoData(data);
        }  
        try {
            gerarPdf(aux);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoPDFActionPerformed

    public void gerarPdf(ArrayList<EmprestimoBEAN> aux) throws IOException {
         
        Document document = new Document();
        
        try {
              
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio.pdf"));
            document.open();
              
            FontSelector seletor = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 28);
            f1.setColor(BaseColor.RED);
            seletor.addFont(f1);
            
            Phrase frase = new Phrase();
            frase = seletor.process("Relatório de Empréstimos\n\n\n");
            
            Paragraph paragrafo = new Paragraph();
            paragrafo.add(frase);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            
            document.add(paragrafo);
            
            PdfPTable table = new PdfPTable(5);

            PdfPCell cell;
            
            f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
            f1.setColor(BaseColor.BLUE);
            seletor = new FontSelector();
            seletor.addFont(f1);
            
            frase = new Phrase();
            frase = seletor.process("Código");
            cell = new PdfPCell(frase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            frase = new Phrase();
            frase = seletor.process("Obra");
            cell = new PdfPCell(frase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            frase = new Phrase();
            frase = seletor.process("Cliente");
            cell = new PdfPCell(frase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            frase = new Phrase();
            frase = seletor.process("D. Emp");
            cell = new PdfPCell(frase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            frase = new Phrase();
            frase = seletor.process("D. Dev");
            cell = new PdfPCell(frase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            for (int i = 0; i < tabela_emp.getRowCount() ; i++) {
                    table.addCell(String.valueOf(tabela_emp.getValueAt(i, 0)));
                    table.addCell(String.valueOf(tabela_emp.getValueAt(i, 1)));
                    table.addCell(String.valueOf(tabela_emp.getValueAt(i, 2)));
                    table.addCell(String.valueOf(tabela_emp.getValueAt(i, 3)));
                    table.addCell(String.valueOf(tabela_emp.getValueAt(i, 4)));
            }

            document.add(table);
              
    }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }
            
          catch (Exception e) {
              System.out.println(e.getMessage());
          }
          
          Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Relatorio.pdf");
          document.close();
    }
    
    private void botaoBusca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBusca1ActionPerformed
        ArrayList<EmprestimoBEAN> aux;
        if (cb_data.isEnabled()) {
            Date data = new Date(cb_data.getDate().getTime());
            aux = controle.findEmprestimoData(data);
        }else{
            Date data =  new Date(new java.util.Date().getTime());
            aux = controle.findEmprestimoData(data);
        }  
        prencherTabela(aux);
    }//GEN-LAST:event_botaoBusca1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBusca1;
    private javax.swing.JButton botaoPDF;
    private com.toedter.calendar.JDateChooser cb_data;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelEditora;
    private javax.swing.JLabel labelTitulo2;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JTable tabela_emp;
    // End of variables declaration//GEN-END:variables
}
