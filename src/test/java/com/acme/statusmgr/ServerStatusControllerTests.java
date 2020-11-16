/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Op() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and is operating normally"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Ext() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=extensions&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and is using these extensions " +
                                "- [Hypervisor, Kubernetes, RAID-6]"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Op() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low," +
                        " and is operating normally"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Ext_Op() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=extensions,operations&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and is using these extensions " +
                            "- [Hypervisor, Kubernetes, RAID-6], and is operating normally"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Ext() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,extensions&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low, " +
                            "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));

    }
    @Test
    public void withParamShouldReturnTailored_Name_Basic_Mem_Ext_Op() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=memory,extensions,operations&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low, " +
                        "and is using these extensions - [Hypervisor, Kubernetes, RAID-6], " +
                        "and is operating normally"));

    }
    @Test
    public void withParamShouldReturnTailored_Basic_Mem_Ext_Op() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=memory,extensions,operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low, " +
                                "and is using these extensions - [Hypervisor, Kubernetes, RAID-6]," +
                                " and is operating normally"));

    }

    /**
     * Testing multiple memories
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_Basic_Mem_Mem_Mem() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=memory,memory,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low, " +
                                "and its memory is running low, and its memory is running low"));

    }

    /**
     * Testing multiples of each
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_Basic_Mem_Mem_Ext_Ext_Op_Mem_Op() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=memory,memory," +
                        "extensions,extensions,operations,memory,operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value(
                        "Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value(
                        "Server is up, and its memory is running low, " +
                                "and its memory is running low, " +
                                "and is using these extensions - [Hypervisor, Kubernetes, RAID-6], " +
                                "and is using these extensions - [Hypervisor, Kubernetes, RAID-6], " +
                                "and is operating normally, " +
                                "and its memory is running low, " +
                                "and is operating normally"));

    }

    /**
     * Testing no details param
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_NoDetailsError() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed"))
                .andDo(print()).andExpect(status().reason
                (is("Required List parameter \'details\' is not present")));
    }

    /**
     * Testing no list passed in to details
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_NoDetailsError2() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details="))
                .andDo(print()).andExpect(status().reason
                (is("Required List parameter \'details\' is not present")));
    }

    /**
     * Testing invalid details argument
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_BadDetailsArg() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=junkError"))
                .andDo(print()).andExpect(status().reason
                (is("Invalid details option")));
    }


    /**
     * Testing invalid details arg with valid details arg
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_BadDetailsArg2() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?details=memory,junkError"))
                .andDo(print()).andExpect(status().reason
                (is("Invalid details option")));
    }



    /**
     * Testing invalid details arg with name and valid details arg
     * @throws Exception
     */
    @Test
    public void withParamShouldReturnTailored_BadDetailsArg3() throws Exception {
        this.mockMvc.perform(get(
                "/server/status/detailed?name=Yaakov&details=memory,junkError"))
                .andDo(print()).andExpect(status().reason
                (is("Invalid details option")));
    }



}
