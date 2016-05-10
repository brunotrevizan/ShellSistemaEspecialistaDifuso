package shell.frame;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import shell.entities.Variavel;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ValoresVariaveisExecucao extends Dialog {

	protected Object result;
	protected Shell shlValoresDasVariveis;
	private Text inputValorVariavel;
	private List<Variavel> variaveis = TelaPrincipal.sistemaEspecialistaDifuso.getVariaveis();
	private Combo comboVariaveis;
	private Button btnOk;
	private Label labelVariaveisCompletas;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ValoresVariaveisExecucao(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlValoresDasVariveis.open();
		shlValoresDasVariveis.layout();
		Display display = getParent().getDisplay();
		while (!shlValoresDasVariveis.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlValoresDasVariveis = new Shell(getParent(), getStyle());
		shlValoresDasVariveis.setSize(239, 149);
		shlValoresDasVariveis.setText("Valores das Vari\u00E1veis");

		Composite composite = new Composite(shlValoresDasVariveis, SWT.NONE);
		composite.setBounds(0, 0, 236, 121);
		composite.setLayout(null);

		Label lblVarivel = new Label(composite, SWT.NONE);
		lblVarivel.setBounds(8, 10, 46, 15);
		lblVarivel.setText("Vari\u00E1vel");

		comboVariaveis = new Combo(composite, SWT.READ_ONLY);
		comboVariaveis.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(comboVariaveis.getText() != ""){
					String valorVariavel = String.valueOf(buscaVariavelPorNome(comboVariaveis.getText()).getValorCrisp());
					inputValorVariavel.setText(valorVariavel);
					
				}
			}
		});
		comboVariaveis.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String valorVariavel = String.valueOf(buscaVariavelPorNome(comboVariaveis.getText()).getValorCrisp());
				inputValorVariavel.setText(valorVariavel);
			}
		});
		comboVariaveis.setBounds(58, 6, 147, 23);

		btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nomeVariavel = comboVariaveis.getText();
				for (Variavel variavel : variaveis) {
					if (variavel.getNome().equals(nomeVariavel)) {
						variavel.setValorCrisp(Integer.parseInt((inputValorVariavel.getText())));
						variavel.popularFaixa();
					}
				}
				TelaPrincipal.sistemaEspecialistaDifuso.setVariaveis(variaveis);
				inputValorVariavel.setText("");
				labelVariaveisCompletas.setVisible(verificaTodasVariaveisPossuemValor());
			}

		});
		btnOk.setBounds(83, 65, 75, 25);
		btnOk.setText("OK");

		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setBounds(8, 40, 43, 15);
		lblValor.setText("Valor");

		inputValorVariavel = new Text(composite, SWT.BORDER);
		inputValorVariavel.setBounds(58, 36, 148, 21);
		inputValorVariavel.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && (chars[i] != '-')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		popularComboVariaveis(comboVariaveis);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLocation(215, 0);
		btnNewButton.setSize(18, 14);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlValoresDasVariveis.close();
			}
		});
		btnNewButton.setText("X");
		
		labelVariaveisCompletas = new Label(composite, SWT.NONE);
		labelVariaveisCompletas.setVisible(false);
		labelVariaveisCompletas.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		labelVariaveisCompletas.setBounds(31, 100, 195, 15);
		labelVariaveisCompletas.setText("Todas as vari\u00E1veis j\u00E1 possuem valor.");
		labelVariaveisCompletas.setVisible(verificaTodasVariaveisPossuemValor());
		
		shlValoresDasVariveis.setLocation(getParent().getLocation());
	}
	
	private boolean verificaTodasVariaveisPossuemValor() {
		for (Variavel variavel : TelaPrincipal.sistemaEspecialistaDifuso.getVariaveis()) {
			if (!variavel.isObjetivo()) {
				if (variavel.getValorCrisp() == null || variavel.getValorCrisp() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private void popularComboVariaveis(Combo combo) {
		String[] itensCombo = new String[variaveis.size() - 1];
		int index = 0;
		for (Variavel variavel : variaveis) {
			if(!variavel.isObjetivo()){
				String itemCombo = variavel.getNome();
				itensCombo[index] = itemCombo;
				index++;
			}
		}
		combo.setItems(itensCombo);
	}

	private Variavel buscaVariavelPorNome(String nomeVariavelUm) {
		for (Variavel variavel : variaveis) {
			if (variavel.getNome().equals(nomeVariavelUm)) {
				return variavel;
			}
		}
		return null;
	}
}
