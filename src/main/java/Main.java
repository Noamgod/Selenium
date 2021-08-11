import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        String userName, password;
        System.out.println(Colors.BLUE+"Please enter your user name ");
        userName= s.next();
        System.out.println(Colors.BLUE+"Please enter your password");
         password= s.next();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\נעם גודלי\\קבצי התקנה\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        driver.manage().window().maximize();
        WebElement personalArea = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il']"));
        personalArea.click();
        WebElement signInName = driver.findElement(By.id("Ecom_User_ID"));
        signInName.sendKeys(userName);
        WebElement signInPassword = driver.findElement(By.id("Ecom_Password"));
        signInPassword.sendKeys(password);
        WebElement singIn = driver.findElement(By.className("submit"));
        singIn.click();

        try {
            WebElement block = driver.findElement(By.id("debtdismissmodal"));
            Thread.sleep(5000);
            block.click();
        }catch (Exception e){
        }

        WebElement personalData = driver.findElement(By.cssSelector("a[href=\"https://moodle.aac.ac.il/login/index.php\"]"));
        personalData.click();
        Thread.sleep(1000);
        WebElement formatList = driver.findElement(By.id("displaydropdown"));
        formatList.click();
        Thread.sleep(500);

        driver.findElement(By.linkText("תקציר הקורס")).click();
        Thread.sleep(5000);
        List<WebElement> webElements = driver.findElements(new By.ByTagName("h6"));
        System.out.println(Colors.CYAN+"Printing list of courses ");
        for (int i = 0; i < webElements.size()-4 ; i++) {
            String text = webElements.get(i).getText();
            System.out.println( Colors.PURPLE+ (i+1) +")" + text);
        }
        System.out.println();
        System.out.println(Colors.GREEN+"Enter the number of the course you want to enter");
        int num=s.nextInt();
        while (num<0 || num> (webElements.size()-4)){
            System.out.println(Colors.GREEN+"Enter the number of the course you want to enter");
            num=s.nextInt();
        }
       WebElement choice = webElements.get(num-1);
        Thread.sleep(1000);
        choice.click();
        System.out.println(Colors.CYAN+"Entered course");
        Thread.sleep(5000);
        System.out.println(Colors.RED+"Logging out");
        WebElement logOutButton =driver.findElement(By.className("userbutton"));
        logOutButton.click();
        WebElement logOut =driver.findElement(By.id("actionmenuaction-6"));
        logOut.click();
        WebElement exit = driver.findElement(By.cssSelector("a[href=\"https://portal.aac.ac.il/AGLogout\"]"));
        exit.click();
    }


}
