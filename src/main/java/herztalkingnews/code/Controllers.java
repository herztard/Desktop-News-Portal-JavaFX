package herztalkingnews.code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;



public class Controllers {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //======== LOG IN ========

    @FXML
    private Hyperlink continueButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label dataIsNotCorrect;
    @FXML
    private CheckBox isAdminCheckBox;

    //======== SIGN UP ========

    @FXML
    private Button createAccountButton;
    @FXML
    private TextField usernameSignUp;
    @FXML
    private TextField emailSignUp;
    @FXML
    private PasswordField passwordSignUp;
    @FXML
    private PasswordField confirmPasswordSignUp;
    @FXML
    private Label signUpMessageField;

    //======== RESET PASSWORD ========

    @FXML
    private TextField usernameResetField;
    @FXML
    private PasswordField newPassResetField;
    @FXML
    private PasswordField confirmNewPassResetField;
    @FXML
    private Button resetButton;
    @FXML
    private Label resetMessageField;

    //======== BUTTONS ========

    @FXML
    private Button technologyButton;
    @FXML
    private Button musicButton;
    @FXML
    private Button eventsButton;
    @FXML
    private Button memesButton;

    @FXML
    private VBox news1;
    @FXML
    private VBox news2;

    //======== WEATHER ========

    @FXML
    private Text weatherField;
    @FXML
    private Hyperlink weatherLink;
    private String weather;
    @FXML
    private TextField city;
    @FXML
    private Text temp_info;
    @FXML
    private Text cloudiness;
    @FXML
    private Text humidity;
    @FXML
    private Button searchButton;
    @FXML
    private Text temp_feels;
    @FXML
    private ImageView image;
    @FXML
    private Text pressure;
    @FXML
    private Text wind;

    //======== REMEMBER ME ========

    public static File file = new File("D:\\CSS108\\Adilzhan_Slyamgazy_220103151_project_2\\src\\main\\resources\\database\\save.txt");

    @FXML
    private CheckBox rememberMeCheckBox;
    @FXML
    public static Label welcomeMessage;

    //======== ADD POST ========

    @FXML
    private TextArea titleTextArea;
    @FXML
    private TextArea contentTextArea;
    @FXML
    private Button uploadImageButton;
    @FXML
    private Text imageNameMessage;
    @FXML
    private Button savePostButton;
    private File selectedFile;
    @FXML
    private Label createPostMessage;
    @FXML
    private Label postCreatedMessage;

    //======== CONTACT US ========

    @FXML
    private Label contactUsMessage;
    @FXML
    private Hyperlink gmailLink;
    @FXML
    private Hyperlink phoneLink;


    @FXML
    protected void technologyButtonOnAction(ActionEvent event){
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/technology-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void memesButtonOnAction(ActionEvent event){
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/memes-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void postButtonOnAction(ActionEvent event){
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/post-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void uploadImageButtonOnAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpeg", "*.jpg"));
        selectedFile = fileChooser.showOpenDialog(stage);
        imageNameMessage.setText(selectedFile.getName());
    }

    @FXML
    protected void savePostButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, FileNotFoundException {
        if (titleTextArea.getText().isEmpty() || contentTextArea.getText().isEmpty() || imageNameMessage.getText().isEmpty()){
            postCreatedMessage.setText("");
            createPostMessage.setText("Please, set title, content and upload an image");
        } else {

            Connection connection = DataBaseCommands.connect();


            String sql = "INSERT INTO POSTS (header, content, date, image) VALUES (?,?,?,?)";

            String title = titleTextArea.getText().trim();
            String content = contentTextArea.getText().trim();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dateTimeFormatter.format(now);
            String pathToImage = selectedFile.getPath();


            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, title);
            prepStatement.setString(2, content);
            prepStatement.setString(3, date);
            prepStatement.setString(4, pathToImage);/*
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            prepStatement.setBinaryStream(4, fileInputStream, (int) selectedFile.length());
            connection.close();
            connection = DataBaseCommands.connect();*/
            prepStatement.executeUpdate();
            connection.close();
            createPostMessage.setText("");
            postCreatedMessage.setText("Post has been successfully added to database");
        }
    }

    @FXML
    protected void getPost(ActionEvent event, int id) throws SQLException, ClassNotFoundException {
        Connection connection = DataBaseCommands.connect();

        String sql = "SELECT header, content, date, pathToImage FROM POSTS WHERE id LIKE " + id;
        Statement statement = connection.createStatement();
        statement.execute(sql);

        connection.close();
    }

    @FXML
    protected void contactUsButtonOnAction(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/contact-us-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void gmailLinkClipboard(){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(gmailLink.getText());
        clipboard.setContent(content);
        contactUsMessage.setText("Copied to clipboard");
    }

    @FXML
    protected void phoneLinkClipboard(){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(phoneLink.getText());
        clipboard.setContent(content);
        contactUsMessage.setText("Copied to clipboard");
    }

    @FXML
    protected void telegramLinkOnAction(){
        DesktopNewsWebSite appInstance = new DesktopNewsWebSite();
        appInstance.telegramLink();
    }

    @FXML
    protected void instagramLinkOnAction(){
        DesktopNewsWebSite appInstance = new DesktopNewsWebSite();
        appInstance.instagramLink();
    }

    @FXML
    protected void githubLinkOnAction(){
        DesktopNewsWebSite appInstace = new DesktopNewsWebSite();
        appInstace.githubLink();
    }

    @FXML
    protected void mainPageButton(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/main-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void signUpPageButtonClick(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/herztalkingnews/views/sign-up-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void resetPassPageButtonClick(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/herztalkingnews/views/reset-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void loginButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, JSONException {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
            dataIsNotCorrect.setText("Please, enter username and password.");
        } else {
            Connection connection = DataBaseCommands.connect();

            String verifyLoginSQL = "SELECT count(1) FROM USERS WHERE username LIKE \"" +
                    usernameTextField.getText().trim() + "\" AND password LIKE \"" + passwordTextField.getText().trim() + "\"";

            try {
                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLoginSQL);

                if (queryResult.getInt(1) == 1){
                    String verifyAdmin = "SELECT count(1) FROM USERS WHERE isAdmin=1 AND username LIKE \"" +
                            usernameTextField.getText().trim() + "\" AND password LIKE \"" + passwordTextField.getText().trim() + "\"";
                    if (isAdminCheckBox.isSelected()){
                        statement = connection.createStatement();
                        queryResult = statement.executeQuery(verifyAdmin);
                        if (queryResult.getInt(1) == 1){
                            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/admin-main-view.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene (root);
                            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
                            stage.setScene(scene);
                            stage.show();
                            connection.close();
                            return;
                        } else {
                            dataIsNotCorrect.setText("You are not an admin");
                            connection.close();
                            return;
                        }
                    }
                    try {
                        if(rememberMeCheckBox.isSelected()){
                            save();
                        }


                        root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/main-view.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene (root);
                        scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    dataIsNotCorrect.setText("The account was not found or incorrect password. Please, try again or sign up.");
                }
            } catch (Exception e){}
        }
    }

    @FXML
    protected void registration(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (usernameSignUp.getText().isEmpty() || emailSignUp.getText().isEmpty() ||
        passwordSignUp.getText().isEmpty() || confirmPasswordSignUp.getText().isEmpty()){
            signUpMessageField.setText("Please, fill in all the fields!");
        } else {
            if (passwordSignUp.getText().equals(confirmPasswordSignUp.getText())){
                Connection connection = DataBaseCommands.connect();

                String username = usernameSignUp.getText().trim();
                String email = emailSignUp.getText().trim();
                String password = passwordSignUp.getText().trim();

                String registerSQL = "INSERT INTO USERS (username, password, email) VALUES(?,?,?)";

                try {
                    PreparedStatement prepStatement = connection.prepareStatement(registerSQL);
                    prepStatement.setString(1, username);
                    prepStatement.setString(2, password);
                    prepStatement.setString(3, email);
                    prepStatement.executeUpdate();

                    try {
                        root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/sign-in-view.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene (root);
                        scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
                        stage.setScene(scene);
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    connection.close();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    signUpMessageField.setText("Username or email address is already being used by another user.");
                }
            } else {
                signUpMessageField.setText("Password does not match!");
            }
        }

    }

    @FXML
    protected void resetPassword(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (usernameResetField.getText().isEmpty() || newPassResetField.getText().isEmpty() ||
        confirmNewPassResetField.getText().isEmpty()){
            resetMessageField.setText("Please, enter username and new password.");
        } else {
            if (newPassResetField.getText().equals(confirmNewPassResetField.getText())){
                Connection connection = DataBaseCommands.connect();
                String usernameLike = usernameResetField.getText().trim();
                String newPassword = newPassResetField.getText().trim();

                boolean isUserExists = false;
                try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM `USERS` WHERE `username` = ?")) {
                    preparedStatement.setString(1, usernameLike);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            isUserExists = true;
                        }
                    }
                }
                try {
                    if (isUserExists){
                        String registerSQL = "UPDATE USERS SET password=? WHERE username=" + "\"" +usernameLike + "\"";
                        connection.close();
                        connection = DataBaseCommands.connect();
                        PreparedStatement prepStatement = connection.prepareStatement(registerSQL);
                        prepStatement.setString(1, newPassword);
                        prepStatement.executeUpdate();

                        try {
                            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/sign-in-view.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
                            stage.setScene(scene);
                            stage.show();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        resetMessageField.setText("There is no user with such username.");
                    }
                } catch (Exception e){
                    resetMessageField.setText("There is no user with such username.");
                }
                connection.close();


            } else {
                resetMessageField.setText("New password does not match!");
            }
        }
    }


    private static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return content.toString();
    }

    @FXML
    protected void searchCity(){

        String getUserCity = city.getText().trim();
        if(!getUserCity.equals("")) {
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" +
                    getUserCity + "&appid=YourAPI&units=metric");

            if (!output.isEmpty()) {
                try{
                    JSONObject jsonObject = new JSONObject(output);

                    temp_info.setText("Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + "°");
                    temp_feels.setText("Feels like: " + jsonObject.getJSONObject("main").getDouble("feels_like") + "°");
                    cloudiness.setText("Cloudiness: " + jsonObject.getJSONObject("clouds").getDouble("all") + "%");
                    humidity.setText("Humidity: " + jsonObject.getJSONObject("main").getDouble("humidity") + "%");
                    pressure.setText("Pressure: " + jsonObject.getJSONObject("main").getDouble("pressure") + " hpa");
                    wind.setText("Wind: " + jsonObject.getJSONObject("wind").getDouble("speed") + "m/s");
                }catch (Exception e){}
            }
        }
    }

    @FXML
    protected void weatherPage(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(Controllers.class.getResource("/herztalkingnews/views/weather-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(Controllers.class.getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void getStartedButtonClick(ActionEvent event) throws IOException {

        if(file.exists()){
            try {
                root = FXMLLoader.load(getClass().getResource("/herztalkingnews/views/main-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene (root);
                scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(getClass().getResource("/herztalkingnews/views/sign-in-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene (root);
                scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    protected void logOutButtonOnAction(ActionEvent event) throws IOException {
        try {
            file.delete();
            root = FXMLLoader.load(getClass().getResource("/herztalkingnews/views/sign-in-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene (root);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            if(!file.exists()) file.createNewFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bufferedWriter.write(usernameTextField.getText());
            bufferedWriter.newLine();
            bufferedWriter.write(passwordTextField.getText());
            bufferedWriter.close();

        } catch (IOException e) { e.printStackTrace(); }
    }
}
