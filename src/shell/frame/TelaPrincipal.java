package shell.frame;

import grafico.Grafico;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import shell.entities.Conectivo;
import shell.entities.PertinenciaRegra;
import shell.entities.Regra;
import shell.entities.SistemaEspecialistaDifuso;
import shell.entities.Valor;
import shell.entities.Variavel;

public class TelaPrincipal extends Shell {
	static Display display = Display.getDefault();
	static TelaPrincipal shell = new TelaPrincipal(display);

	private Text inputNomeVariavel;
	private Button checkObjetivo;
	private List listaVariaveis;
	public static SistemaEspecialistaDifuso sistemaEspecialistaDifuso;
	private Button btnRemoverVariavel;
	private Button btnInserirRegra;
	private List listaRegras;
	private Text textResultado;

	public static void main(String args[]) {
		try {
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TelaPrincipal(Display display) {
		super(display, SWT.CLOSE | SWT.MIN | SWT.TITLE);
		setLayout(null);

		sistemaEspecialistaDifuso = new SistemaEspecialistaDifuso();
		
		preparaCasoDeTeste();
		
		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBounds(0, 0, 530, 410);

		TabItem tbtmCadastroDeVariveis = new TabItem(tabFolder, SWT.NONE);
		tbtmCadastroDeVariveis.setText("Cadastro de Vari\u00E1veis");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmCadastroDeVariveis.setControl(composite);
		composite.setLayout(null);

		Label labelNome = new Label(composite, SWT.NONE);
		labelNome.setBounds(10, 27, 46, 15);
		labelNome.setText("Nome");

		Label labelObjetivo = new Label(composite, SWT.NONE);
		labelObjetivo.setBounds(10, 52, 55, 15);
		labelObjetivo.setText("Objetivo");

		inputNomeVariavel = new Text(composite, SWT.BORDER);
		inputNomeVariavel.setBounds(71, 24, 126, 21);

		checkObjetivo = new Button(composite, SWT.CHECK);
		checkObjetivo.setBounds(71, 51, 18, 16);

		listaVariaveis = new List(composite, SWT.BORDER);
		listaVariaveis.setBounds(265, 10, 247, 362);

		Button btnAdicionarVarivel = new Button(composite, SWT.NONE);
		btnAdicionarVarivel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				adicionarVariavel();
			}
		});
		btnAdicionarVarivel.setBounds(10, 91, 107, 25);
		btnAdicionarVarivel.setText("Adicionar Vari\u00E1vel");

		Button btnDefinirValores = new Button(composite, SWT.NONE);
		btnDefinirValores.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ValoresVariaveis dialogValoresVariaveis = new ValoresVariaveis(shell, SWT.APPLICATION_MODAL);
				if (listaVariaveis.getSelection().length == 0) {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Selecione uma variável");
					dialog.setMessage("Selecione uma variável para editar");
					dialog.open();
				} else {
					for (String nomeVariavel : listaVariaveis.getSelection()) {
						for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
							if (variavel.getNome().equals(nomeVariavel)) {
								dialogValoresVariaveis.setVariavel(variavel);
							}
						}
					}
					dialogValoresVariaveis.open();
				}
			}
		});
		btnDefinirValores.setBounds(154, 91, 88, 25);
		btnDefinirValores.setText("Definir Valores");

		btnRemoverVariavel = new Button(composite, SWT.NONE);
		btnRemoverVariavel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] variavelSelecionada = listaVariaveis.getSelection();
				java.util.List<Variavel> listaVariaveisRemovidas = new ArrayList<Variavel>();
				for (String string : variavelSelecionada) {
					for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
						if (variavel.getNome().equals(string)) {
							listaVariaveisRemovidas.add(variavel);
							listaVariaveis.remove(listaVariaveis.getSelectionIndex());
						}
					}
				}
				sistemaEspecialistaDifuso.getVariaveis().removeAll(listaVariaveisRemovidas);
			}
		});
		btnRemoverVariavel.setBounds(154, 122, 85, 25);
		btnRemoverVariavel.setText("Remover");
		
		Button btnGerarGrfico = new Button(composite, SWT.NONE);
		btnGerarGrfico.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Grafico grafico = new Grafico();
				if (listaVariaveis.getSelection().length == 1) {
					String nomeVariavel = listaVariaveis.getSelection()[0];
					for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
						if (variavel.getNome().equals(nomeVariavel)) {
							grafico.abrirGrafico(variavel);
							break;
						}
					}
				}
			}
		});
		btnGerarGrfico.setText("Gerar Gr\u00E1fico");
		btnGerarGrfico.setBounds(154, 153, 85, 25);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Cadastro de Regras");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(null);

		btnInserirRegra = new Button(composite_1, SWT.NONE);
		btnInserirRegra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (sistemaEspecialistaDifuso.getVariaveis().isEmpty()) {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Cadastre pelo menos uma variável");
					dialog.setMessage("É necessário cadastrar pelo menos uma variável");
					dialog.open();
				} else {
					DialogRegras dialogRegras = new DialogRegras(shell, SWT.APPLICATION_MODAL);
					dialogRegras.setVariaveis(sistemaEspecialistaDifuso.getVariaveis());
					dialogRegras.open();
					listaRegras.removeAll();
					popularListaRegras();
				}
			}

		});
		btnInserirRegra.setBounds(10, 29, 89, 25);
		btnInserirRegra.setText("Inserir Regra");

		listaRegras = new List(composite_1, SWT.BORDER);
		listaRegras.setBounds(113, 10, 399, 362);

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (listaRegras.getSelection().length > 0) {
					int indiceRegra = Integer.parseInt(listaRegras.getSelection()[0].split(":")[0]);
					java.util.List<Regra> listaRegrasRemovidas = new ArrayList<Regra>();
					for (Regra regra : sistemaEspecialistaDifuso.getRegras()) {
						if (regra.getIdUnico() == indiceRegra) {
							listaRegrasRemovidas.add(regra);
						}
					}
					sistemaEspecialistaDifuso.getRegras().removeAll(
							listaRegrasRemovidas);
					listaRegras.removeAll();
					popularListaRegras();
				} else {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Selecione uma regra");
					dialog.setMessage("É necessário selecionar uma regra para excluir");
					dialog.open();
				}
			}
		});
		btnNewButton.setBounds(10, 61, 89, 25);
		btnNewButton.setText("Excluir Regra");
		
		TabItem tbtmExecuo = new TabItem(tabFolder, SWT.NONE);
		tbtmExecuo.setText("Execu\u00E7\u00E3o");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmExecuo.setControl(composite_2);
		composite_2.setLayout(null);
		
		Button btnExecutarSistemaEspecialista = new Button(composite_2, SWT.APPLICATION_MODAL);
		btnExecutarSistemaEspecialista.addSelectionListener(new SelectionAdapter() {
			@Override
					public void widgetSelected(SelectionEvent e) {
						zerarParametrosParaExecucao();
						if (validarSistemaEspecialistaDifuso()) {
							ValoresVariaveisExecucao valoresVariaveisExecucao = new ValoresVariaveisExecucao(shell, SWT.NONE);
							valoresVariaveisExecucao.open();
							
							java.util.List<PertinenciaRegra> listaPertinencias = popularListaPertinenciaRegra();
							
							Variavel objetivo = obtemVariavelObjetivo();
							
							for (PertinenciaRegra pertinenciaRegra : listaPertinencias) {
								for (Valor valor : objetivo.getValores()) {
									if(pertinenciaRegra.getRegra().getValorVariavelTres().getNome().equals(valor.getNome())){
										if(pertinenciaRegra.getValorPertinencia() > valor.getValorFuzzyObjetivo()){
											valor.setValorFuzzyObjetivo(pertinenciaRegra.getValorPertinencia());
										}
									}
								}
							}

							//dividendo = cima, divisor =  baixo

							double dividendo = 0.0;
							double divisor = 0.0;
							
							for (Valor valor : objetivo.getValores()) {
								dividendo += obterDividendoDoValorParametro(valor,valor.getInicioSuporte(),valor.getFimSuporte()) * valor.getValorFuzzyObjetivo();
								divisor += obterDivisorDoValorParametro(valor,valor.getInicioSuporte(),valor.getFimSuporte()) * valor.getValorFuzzyObjetivo();
							}

							textResultado.setText("Resultado:" + dividendo/divisor);
						}
					}

		});
		
		btnExecutarSistemaEspecialista.setBounds(132, 36, 255, 42);
		btnExecutarSistemaEspecialista.setText("Executar Sistema Especialista Difuso");
		
		textResultado = new Text(composite_2, SWT.BORDER);
		textResultado.setBounds(36, 104, 448, 251);
		
		Label lblResultado = new Label(composite_2, SWT.NONE);
		lblResultado.setBounds(36, 83, 63, 15);
		lblResultado.setText("Resultado:");
		populaListaVariaveisTela();
		popularListaRegras();
		
		createContents();
	}

	private void zerarParametrosParaExecucao() {
		textResultado.setText("");
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			variavel.setValorCrisp(0);
		}
	}
	
	private double obterDivisorDoValorParametro(Valor valor, int inicioSuporte, int fimSuporte) {
		boolean condicaoSaida = true;
		double indice = inicioSuporte;
		double retorno = 0;
		
		while(condicaoSaida){
			if(indice >= fimSuporte){
				condicaoSaida = false;
			}
			
			if(condicaoSaida){
				retorno ++;
			}
			
			indice += 10;
		}
		
		if(fimSuporte > 0){
			retorno ++;
		}
		
		return retorno;
	}

	private double obterDividendoDoValorParametro(Valor valor, double inicioSuporte, double fimSuporte) {
		boolean condicaoSaida = true;
		double indice = inicioSuporte;
		double retorno = 0;
		
		while(condicaoSaida){
			if(indice >= fimSuporte){
				condicaoSaida = false;
			}
			
			if(condicaoSaida){
				retorno += indice;
			}
			
			indice += 10;
		}
		
		retorno += fimSuporte;
		
		return retorno;
	}

	private Variavel obtemVariavelObjetivo() {
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			if (variavel.isObjetivo()) {
				return variavel;
			}
		}
		return null;
	}

	protected boolean validarSistemaEspecialistaDifuso() {
		boolean autorizarExecucao = false;
		if(validarVariaveisPossuiVariavelObjetivo()){
			autorizarExecucao = true;
		}
		return autorizarExecucao;
	}

	private double calculaPertinenciaRegra(Regra regra) {
		if (regra.getConectivo().equals(Conectivo.OU)) {
			return Math.max(regra.getValorVariavelUm().getPertinencia(regra.getVariavelUm().getValorCrisp()), regra.getValorVariavelDois()
					.getPertinencia(regra.getVariavelDois().getValorCrisp()));
		}
		return Math.min(regra.getValorVariavelUm().getPertinencia(regra.getVariavelUm().getValorCrisp()), regra.getValorVariavelDois()
				.getPertinencia(regra.getVariavelDois().getValorCrisp()));
	}

	@SuppressWarnings("unused")
	private void executaTestePertinencias() {
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			System.out.println(variavel.getNome());
			for (Valor valor : variavel.getValores()) {
				System.out.println(valor.getNome());
				if(variavel.getNome().equalsIgnoreCase("Velocidade")){
					System.out.println(valor.getPertinencia(105));
				}else{
					System.out.println(valor.getPertinencia(80));
				}
			}
		}
	}
	
	private boolean validarVariaveisPossuiVariavelObjetivo() {
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			if(variavel.isObjetivo()){
				return true;
			}
		}
		return false;
	}

	private java.util.List<PertinenciaRegra> popularListaPertinenciaRegra() {
		java.util.List<PertinenciaRegra> listaPertinencias = new ArrayList<PertinenciaRegra>();
		
		for (Regra regra : sistemaEspecialistaDifuso.getRegras()) {
			PertinenciaRegra pertinenciaRegra = new PertinenciaRegra();
			pertinenciaRegra.setRegra(regra);
			pertinenciaRegra.setValorPertinencia(calculaPertinenciaRegra(regra));
			listaPertinencias.add(pertinenciaRegra);
		}
		return listaPertinencias;
	}
	
	private void preparaCasoDeTeste(){
		Variavel chuva = new Variavel();
		chuva.setNome("Chuva");
		Valor fraca = new Valor();
		fraca.setNome("fraca");
		fraca.setInicioSuporte(0);
		fraca.setFimSuporte(20);
		fraca.setInicioNucleo(0);
		fraca.setFimNucleo(10);
		chuva.getValores().add(fraca);
		
		Valor medio = new Valor();
		medio.setNome("medio");
		medio.setInicioSuporte(15);
		medio.setFimSuporte(30);
		medio.setInicioNucleo(22);
		medio.setFimNucleo(22);
		chuva.getValores().add(medio);
		
		Valor forte = new Valor();
		forte.setNome("forte");
		forte.setInicioSuporte(25);
		forte.setFimSuporte(45);
		forte.setInicioNucleo(35);
		forte.setFimNucleo(45);
		chuva.getValores().add(forte);
		
		Variavel nivel = new Variavel();
		nivel.setNome("Nivel");
		Valor baixoNivel = new Valor();
		baixoNivel.setNome("baixo");
		baixoNivel.setInicioSuporte(0);
		baixoNivel.setFimSuporte(5);
		baixoNivel.setInicioNucleo(0);
		baixoNivel.setFimNucleo(2);
		nivel.getValores().add(baixoNivel);
		
		Valor medioNivel = new Valor();
		medioNivel.setNome("medio");
		medioNivel.setInicioSuporte(3);
		medioNivel.setFimSuporte(8);
		medioNivel.setInicioNucleo(6);
		medioNivel.setFimNucleo(6);
		nivel.getValores().add(medioNivel);
		
		Valor altoNivel = new Valor();
		altoNivel.setNome("medio");
		altoNivel.setInicioSuporte(7);
		altoNivel.setFimSuporte(12);
		altoNivel.setInicioNucleo(9);
		altoNivel.setFimNucleo(12);
		nivel.getValores().add(altoNivel);
		
		Variavel cheia = new Variavel();
		cheia.setNome("Cheia");
		Valor baixoCheia = new Valor();
		baixoCheia.setNome("baixo");
		baixoCheia.setInicioSuporte(0);
		baixoCheia.setFimSuporte(45);
		baixoCheia.setInicioNucleo(0);
		baixoCheia.setFimNucleo(25);
		cheia.getValores().add(baixoCheia);
		
		Valor medioCheia = new Valor();
		medioCheia.setNome("medio");
		medioCheia.setInicioSuporte(35);
		medioCheia.setFimSuporte(75);
		medioCheia.setInicioNucleo(50);
		medioCheia.setFimNucleo(50);
		cheia.getValores().add(medioCheia);
		
		Valor altoCheia = new Valor();
		altoCheia.setNome("alto");
		altoCheia.setInicioSuporte(70);
		altoCheia.setFimSuporte(100);
		altoCheia.setInicioNucleo(80);
		altoCheia.setFimNucleo(100);
		cheia.getValores().add(altoCheia);
		cheia.setObjetivo(true);
		
		sistemaEspecialistaDifuso.getVariaveis().add(chuva);
		sistemaEspecialistaDifuso.getVariaveis().add(nivel);
		sistemaEspecialistaDifuso.getVariaveis().add(cheia);
		
		Regra regraUm = new Regra();
		regraUm.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraUm.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraUm.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraUm.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(2));
		regraUm.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(2));
		regraUm.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(2));
		regraUm.setConectivo(Conectivo.E);
		regraUm.setIdUnico(0);
		
		sistemaEspecialistaDifuso.getRegras().add(regraUm);
		
		Regra regraDois = new Regra();
		regraDois.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraDois.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraDois.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraDois.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(1));
		regraDois.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(2));
		regraDois.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(2));
		regraDois.setConectivo(Conectivo.E);
		regraDois.setIdUnico(1);
		
		sistemaEspecialistaDifuso.getRegras().add(regraDois);
		
		Regra regraTres = new Regra();
		regraTres.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraTres.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraTres.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraTres.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(0));
		regraTres.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(0));
		regraTres.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(0));
		regraTres.setConectivo(Conectivo.E);
		regraTres.setIdUnico(2);
		
		sistemaEspecialistaDifuso.getRegras().add(regraTres);
		
		Regra regraQuatro = new Regra();
		regraQuatro.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraQuatro.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraQuatro.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraQuatro.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(0));
		regraQuatro.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(0));
		regraQuatro.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(0));
		regraQuatro.setConectivo(Conectivo.E);
		regraQuatro.setIdUnico(3);
		
		sistemaEspecialistaDifuso.getRegras().add(regraQuatro);
		
		Regra regraCinco = new Regra();
		regraCinco.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraCinco.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraCinco.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraCinco.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(0));
		regraCinco.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(2));
		regraCinco.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(1));
		regraCinco.setConectivo(Conectivo.E);
		regraCinco.setIdUnico(4);
		
		sistemaEspecialistaDifuso.getRegras().add(regraCinco);
		
		Regra regraSeis = new Regra();
		regraSeis.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraSeis.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraSeis.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraSeis.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(2));
		regraSeis.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(0));
		regraSeis.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(1));
		regraSeis.setConectivo(Conectivo.E);
		regraSeis.setIdUnico(5);
		
		sistemaEspecialistaDifuso.getRegras().add(regraSeis);
		
		Regra regraSete = new Regra();
		regraSete.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraSete.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraSete.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraSete.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(1));
		regraSete.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(1));
		regraSete.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(1));
		regraSete.setConectivo(Conectivo.E);
		regraSete.setIdUnico(6);
		
		sistemaEspecialistaDifuso.getRegras().add(regraSete);
	}
	
	@SuppressWarnings("unused")
    private void preparaCasoDeTesteProvaDois() {
		Variavel velocidade = new Variavel();
		velocidade.setNome("Velocidade");
		Valor baixa = new Valor();
		baixa.setNome("baixa");
		baixa.setInicioSuporte(0);
		baixa.setFimSuporte(60);
		baixa.setInicioNucleo(0);
		baixa.setFimNucleo(30);
		velocidade.getValores().add(baixa);
		
		Valor media = new Valor();
		media.setNome("media");
		media.setInicioSuporte(35);
		media.setFimSuporte(115);
		media.setInicioNucleo(75);
		media.setFimNucleo(75);
		velocidade.getValores().add(media);
		
		Valor alta = new Valor();
		alta.setNome("alta");
		alta.setInicioSuporte(90);
		alta.setFimSuporte(150);
		alta.setInicioNucleo(120);
		alta.setFimNucleo(150);
		velocidade.getValores().add(alta);
		
		Variavel distancia = new Variavel();
		distancia.setNome("Distancia");
		Valor pequena = new Valor();
		pequena.setNome("pequena");
		pequena.setInicioSuporte(0);
		pequena.setFimSuporte(90);
		pequena.setInicioNucleo(0);
		pequena.setFimNucleo(50);
		distancia.getValores().add(pequena);
		
		Valor mediaDistancia = new Valor();
		mediaDistancia.setNome("media");
		mediaDistancia.setInicioSuporte(60);
		mediaDistancia.setFimSuporte(140);
		mediaDistancia.setInicioNucleo(100);
		mediaDistancia.setFimNucleo(100);
		distancia.getValores().add(mediaDistancia);
		
		Valor grande = new Valor();
		grande.setNome("grande");
		grande.setInicioSuporte(110);
		grande.setFimSuporte(200);
		grande.setInicioNucleo(150);
		grande.setFimNucleo(200);
		distancia.getValores().add(grande);
		
		Variavel frenagem = new Variavel();
		frenagem.setObjetivo(true);
		frenagem.setNome("Frenagem");
		Valor negativo = new Valor();
		negativo.setNome("negativo");
		negativo.setInicioSuporte(-100);
		negativo.setFimSuporte(0);
		negativo.setInicioNucleo(-50);
		negativo.setFimNucleo(0);
		frenagem.getValores().add(negativo);
		
		Valor zero = new Valor();
		zero.setNome("zero");
		zero.setInicioSuporte(-50);
		zero.setFimSuporte(50);
		zero.setInicioNucleo(0);
		zero.setFimNucleo(0);
		frenagem.getValores().add(zero);
		
		Valor positivo = new Valor();
		positivo.setNome("positivo");
		positivo.setInicioSuporte(0);
		positivo.setFimSuporte(100);
		positivo.setInicioNucleo(50);
		positivo.setFimNucleo(100);
		frenagem.getValores().add(positivo);
		
		sistemaEspecialistaDifuso.getVariaveis().add(velocidade);
		sistemaEspecialistaDifuso.getVariaveis().add(distancia);
		sistemaEspecialistaDifuso.getVariaveis().add(frenagem);
		
		
		Regra regraUm = new Regra();
		regraUm.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraUm.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraUm.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraUm.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(2));
		regraUm.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(0));
		regraUm.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(0));
		regraUm.setConectivo(Conectivo.E);
		regraUm.setIdUnico(0);
		
		Regra regraDois = new Regra();
		regraDois.setVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(0));
		regraDois.setVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(1));
		regraDois.setVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2));
		
		regraDois.setValorVariavelUm(sistemaEspecialistaDifuso.getVariaveis().get(0).getValores().get(1));
		regraDois.setValorVariavelDois(sistemaEspecialistaDifuso.getVariaveis().get(1).getValores().get(1));
		regraDois.setValorVariavelTres(sistemaEspecialistaDifuso.getVariaveis().get(2).getValores().get(1));
		regraDois.setConectivo(Conectivo.E);
		regraDois.setIdUnico(1);
		
		sistemaEspecialistaDifuso.getRegras().add(regraUm);
		sistemaEspecialistaDifuso.getRegras().add(regraDois);
	}

	private void popularListaRegras() {
		for (Regra regra : sistemaEspecialistaDifuso.getRegras()) {
			listaRegras.add(formataRegra(regra));
		}

	}

	private String formataRegra(Regra regra) {
		return regra.getIdUnico() + ": SE " + regra.getVariavelUm().getNome() + " = " + regra.getValorVariavelUm().getNome() + " " + regra.getConectivo().name() + " "
				+ regra.getVariavelDois().getNome() + " = " + regra.getValorVariavelDois().getNome() + " ENTÃO "
				+ regra.getVariavelTres().getNome() + " = " + regra.getValorVariavelTres().getNome();
	}
	
	private void populaListaVariaveisTela() {
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			listaVariaveis.add(variavel.getNome());
		}
	}

	public void adicionarVariavel() {
		String mensagem = "";
		String nomeVariavel = inputNomeVariavel.getText();
		if (nomeVariavel == null || nomeVariavel.equals("")) {
			mensagem += "Defina um nome para a variável";
		}

		if (variavelDuplicada(nomeVariavel)) {
			mensagem += "Já existe uma variável com esse nome.";
		}

		if (mensagem.equals("")) {
			Variavel variavel = new Variavel();
			variavel.setNome(nomeVariavel);
			variavel.setObjetivo(checkObjetivo.getSelection());
			listaVariaveis.add(variavel.getNome());
			sistemaEspecialistaDifuso.getVariaveis().add(variavel);
		} else {
			MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			dialog.setText("Variável Duplicada");
			dialog.setMessage(mensagem);
			dialog.open();
		}

		zerarCampos();
	}

	private void zerarCampos() {
		inputNomeVariavel.setText("");
		checkObjetivo.setSelection(false);
	}

	private boolean variavelDuplicada(String nomeVariavel) {
		for (Variavel variavel : sistemaEspecialistaDifuso.getVariaveis()) {
			if (variavel.getNome().equals(nomeVariavel)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Shell");
		setSize(546, 449);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
