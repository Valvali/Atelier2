/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.control;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author bertrand
 */
public class JsonError {
    public static JsonObject error(String msg) {
        return Json.createObjectBuilder().add("error", msg).build();
    }
}
