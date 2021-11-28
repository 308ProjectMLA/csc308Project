package com.example.csc308project;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ModifyPermController {
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
            boolean updated = manifestParser.addPermission("group", gRAddName, 'r' );
            if (!updated)
                message += "Error: Group " + gRAddName + " already has read access to " + fileName + "\n";
            else
                message += "Success: Group " + gRAddName + " granted read access to " + fileName + "\n";
        }
        if(gWAddName != null){
            boolean updated = manifestParser.addPermission("group", gWAddName, 'w' );
            if (!updated)
                message += "Error: Group " + gWAddName + " already has write access to " + fileName + "\n";
            else
                message += "Success: Group " + gWAddName + " granted write access to " + fileName + "\n";
        }
        if(gRRemoveName != null){
            boolean updated = manifestParser.removePermission("group", gRRemoveName, 'r' );
            if (!updated)
                message += "Error: Group " + gRRemoveName + " already does not have read access to " + fileName + "\n";
            else
                message += "Success: Group " + gRRemoveName + " read access removed from " + fileName + "\n";
        }
        if(gWRemoveName != null){
            boolean updated = manifestParser.removePermission("group", gWRemoveName, 'w' );
            if (!updated)
                message += "Error: Group " + gWRemoveName +  " already does not have write access to " + fileName + "\n";
            else
                message += "Success: Group " + gWRemoveName + " write access removed from " + fileName + "\n";
        }
        if(uRAddName != null){
            boolean updated = manifestParser.addPermission("user", uRAddName, 'r' );
            if (!updated)
                message += "Error: User " + uRAddName + " already has read access to " + fileName + "\n";
            else
                message += "Success: User " + uRAddName + " granted read access to " + fileName + "\n";
        }
        if(uWAddName != null){
            boolean updated = manifestParser.addPermission("user", uWAddName, 'w' );
            if (!updated)
                message += "Error: User " + uWAddName + " already has write access to " + fileName + "\n";
            else
                message += "Success: User " + uWAddName + " granted write access to " + fileName + "\n";
        }
        if(uRRemoveName != null){
            boolean updated = manifestParser.removePermission("user", uRRemoveName, 'r' );
            if (!updated)
                message += "Error: User " + uRRemoveName + " already does not have read access to " + fileName + "\n";
            else
                message += "Success: User " + uRRemoveName + " read access removed from " + fileName + "\n";
        }
        if(uWRemoveName != null){
            boolean updated = manifestParser.removePermission("user", uWRemoveName, 'w' );
            if (!updated)
                message += "Error: User " + uWRemoveName + " already does not have write access to " + fileName + "\n";
            else
                message += "Success: User " + uWRemoveName + " write access removed from " + fileName + "\n";
        }

        return message;
    }
}
