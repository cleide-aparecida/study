package suporte;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Utils {



    public static WebDriver iniciarNavegador() {
        //Abrir o navegador
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //navegando para a pagina do taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;

    }
    public static WebDriver fazerLogin(String user, String pass, WebDriver navegador) {

        //Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        //Clicar no campo com o nome "login" que esta dentro do formulário de id "signinbox"
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).click();

        // Digitar no campo com o nome "login" que esta dentro do formulário de id "signinbox" o texto "julio0001"
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(user);

        //Clicar no campo com o nome "password" que esta dentro do formulário de id "signinbox"
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).click();

        // Digitar no campo com o nome "passsword" que esta dentro do formulário de id "signinbox" o texto "123456"
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(pass);

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.id("signinbox")).findElement(By.linkText("SIGN IN")).click();

        return navegador;

    }

    public static void fecharNavegador(WebDriver navegador){
        navegador.quit();
    }
}
