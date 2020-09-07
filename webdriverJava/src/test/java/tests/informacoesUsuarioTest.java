package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Utils;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTest.csv")
public class informacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Utils.iniciarNavegador();
    }

    @Test
    public void testAdicionarUmInformacaoAdicionalDoUsuario(@Param(name = "usuario") String user,@Param(name = "senha") String pass) {
        navegador = Utils.fazerLogin(user, pass , navegador);
        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "More data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar o popup onde esta o formulário de id "addmoredata"
        WebElement popupaddmoredata = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupaddmoredata.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // No campo de name "contract" digitar "+55999999999"
        popupaddmoredata.findElement(By.name("contact")).sendKeys("+5535999999999");

        // Clicar no link de text "Save" que esta na popup
        popupaddmoredata.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!", mensagem);
    }

    @Test
    public void removerUmcontatoDeUmUsuario() {
        navegador = Utils.fazerLogin("julio0001", "123456", navegador);
        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "More data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        //Clicar no elemento pelo seu xpath //span[text()="+5535999999999"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5535999999999\"]/following-sibling::a")).click();

        //Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "./target/screenshots/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10s  para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto  "Logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown() {
        // Fechar o navegador
        Utils.fecharNavegador(navegador);
    }
}
