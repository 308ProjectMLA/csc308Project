package com.example.csc308project;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ModifyPermController {

    private static final String GROUP_ERR = "Error: Group ";
    private static final String GROUP_SUCC = "Success: Group ";
    private static final String USER_ERR = "Error: User ";
    private static final String USER_SUCC = "Success: User ";

    private ModifyPermController() {
        throw new IllegalStateException();
    }

    public static String processPermChange(String fileName, String gRAddName, String gWAddName,
                                          String gRRemoveName, String gWRemoveName,
                                          String uRAddName, String uWAddName,
                                          String uRRemoveName, String uWRemoveName) throws IOException, ParseException {

        String message = "Permission Modification Submitted\n";

        if(fileName == null){
            message += "Error: Please select a file to modify\n";
        }

        ManifestParser manifestParser = new ManifestParser(fileName);
        if(gRAddName != null){
            boolean updated = manifestParser.addPermission(ManifestParser.GROUP_TAG, gRAddName, 'r' );
            if (!updated)
                message += GROUP_ERR + gRAddName + " already has read access to " + fileName + "\n";
            else
                message += GROUP_SUCC + gRAddName + " granted read access to " + fileName + "\n";
        }
        if(gWAddName != null){
            boolean updated = manifestParser.addPermission(ManifestParser.GROUP_TAG, gWAddName, 'w' );
            if (!updated)
                message += GROUP_ERR + gWAddName + " already has write access to " + fileName + "\n";
            else
                message += GROUP_SUCC + gWAddName + " granted write access to " + fileName + "\n";
        }
        if(gRRemoveName != null){
            boolean updated = manifestParser.removePermission(ManifestParser.GROUP_TAG, gRRemoveName, 'r' );
            if (!updated)
                message += GROUP_ERR + gRRemoveName + " already does not have read access to " + fileName + "\n";
            else
                message += GROUP_SUCC + gRRemoveName + " read access removed from " + fileName + "\n";
        }
        if(gWRemoveName != null){
            boolean updated = manifestParser.removePermission(ManifestParser.GROUP_TAG, gWRemoveName, 'w' );
            if (!updated)
                message += GROUP_ERR + gWRemoveName +  " already does not have write access to " + fileName + "\n";
            else
                message += GROUP_SUCC + gWRemoveName + " write access removed from " + fileName + "\n";
        }
        if(uRAddName != null){
            boolean updated = manifestParser.addPermission(ManifestParser.USER_TAG, uRAddName, 'r' );
            if (!updated)
                message += USER_ERR + uRAddName + " already has read access to " + fileName + "\n";
            else
                message += USER_SUCC + uRAddName + " granted read access to " + fileName + "\n";
        }
        if(uWAddName != null){
            boolean updated = manifestParser.addPermission(ManifestParser.USER_TAG, uWAddName, 'w' );
            if (!updated)
                message += USER_ERR + uWAddName + " already has write access to " + fileName + "\n";
            else
                message += USER_SUCC + uWAddName + " granted write access to " + fileName + "\n";
        }
        if(uRRemoveName != null){
            boolean updated = manifestParser.removePermission(ManifestParser.USER_TAG, uRRemoveName, 'r' );
            if (!updated)
                message += USER_ERR + uRRemoveName + " already does not have read access to " + fileName + "\n";
            else
                message += USER_SUCC + uRRemoveName + " read access removed from " + fileName + "\n";
        }
        if(uWRemoveName != null){
            boolean updated = manifestParser.removePermission(ManifestParser.USER_TAG, uWRemoveName, 'w' );
            if (!updated)
                message += USER_ERR + uWRemoveName + " already does not have write access to " + fileName + "\n";
            else
                message += USER_SUCC + uWRemoveName + " write access removed from " + fileName + "\n";
        }

        return message;
    }
}
