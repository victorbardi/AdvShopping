import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class AdvLogin {

    private WebDriver navegador;

        @Before
        public void setup() {

        System.setProperty("webdriver.chrome.driver" , "C:\\Users\\victo\\chromedriver\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();

        class DualPrintStream extends PrintStream {
            private final PrintStream second;

            DualPrintStream(OutputStream main, PrintStream second){
            super(main);
            this.second = second;
            }

            @Override
            public void write(byte[] b) throws IOException{
            super.write(b);
            second.write(b);
            }
        }
    }

        private void takeSnapShot(WebDriver webDriver, String fileWithPath) throws Exception {
            TakesScreenshot scrShot =((TakesScreenshot) webDriver);
            File ScrFile =scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile =new File(fileWithPath);
            FileUtils.copyFile(ScrFile , DestFile);
        }

        @Test
    public void AdvantadgeShoppingLogin() throws Exception{

            //Abrindo site
            navegador.get("https://advantageonlineshopping.com/#/");

            Thread.sleep(10000);

            //Abrir menu para realizar login
            navegador.findElement(By.xpath("/html/body/header/nav/ul/li[3]/a")).click();

            Thread.sleep(5000);

            //Usuário
            String user = ("SRTestBox2");
            navegador.findElement(By.xpath("/html/body/login-modal/div/div/div[3]/sec-form/sec-view[1]/div/input")).sendKeys(user);

            //Senha
            String senha = ("Bmth10!");
            navegador.findElement(By.xpath("/html/body/login-modal/div/div/div[3]/sec-form/sec-view[2]/div/input")).sendKeys(senha);

            Thread.sleep(3000);

            //Confirmar o login
            navegador.findElement(By.xpath("/html/body/login-modal/div/div/div[3]/sec-form/sec-sender/button")).click();

            Thread.sleep(3000);

            //Validação do teste
            WebElement validateUser = navegador.findElement(By.xpath("/html/body/header/nav/ul/li[3]/a"));
            System.out.println("\n Login"+ validateUser.getText() + " feito com sucesso! Teste finalizado!");
            Assert.assertEquals(validateUser.getText(), user);

            Thread.sleep(3000);

            this.takeSnapShot(navegador, "C:\\Users\\victo\\IdeaProjects\\AdvShoppingUpdate\\logs\\AdvLogin\\AdvLogin.png");

        }

        @After
    public void tearDown() {
    navegador.quit();
        }
}
