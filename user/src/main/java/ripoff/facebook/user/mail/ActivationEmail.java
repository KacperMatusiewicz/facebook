package ripoff.facebook.user.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ActivationEmail {

    private String recipientAddress;
    private String name;
    private String key;
    private static final String port = "8081";

    public String getEmailBody() {
        return  "<h1 align=\"center\">Hello, " + name + "!</h1>\n" +
                "<p align=\"center\">Please click the following button to activate your account:</p>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box; min-width: 100% !important;\" width=\"100%\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\" valign=\"top\">\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                <tr>\n" +
                "                    <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\" valign=\"top\" bgcolor=\"#3498db\" align=\"center\"> <a href=\"http://localhost:" + port + "/api/v1/activate/" + key + "\" style=\"display: inline-block; color: #ffffff; background-color: #3498db; border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer; text-decoration: none; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-transform: capitalize; border-color: #3498db;\">Activate now!</a> </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n";
    }
}
