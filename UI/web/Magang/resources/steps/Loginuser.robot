*** Settings ***
Library      SeleniumLibrary
# Library      RPA.Excel.Files
# Library      OTP
Variables    ${CURDIR}/../../resources/locator/Login.py
# Variables    ${CURDIR}/../../resources/locator/DataTest.py
# Resource     ${CURDIR}/../../resources/helper/env.robot
Resource     ${CURDIR}/../../resources/steps/Loginuser.robot


*** Keywords ***
# Open Web FMS Sera
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read worksheet   browser   header=${TRUE}
#     FOR    ${row}    IN    @{data}
#         ${url}=           Set Variable    ${row}[url]   
#         ${browser}=       Set Variable    ${row}[browser] 
#         # Open browser
#         open browser                     ${url}        ${browser}
#         # Maximize Browser Window
#         Set Window Size    1920    1080
#         sleep                            4s
#         SeleniumLibrary.element should be visible        ${FormLogin}       60s
#         sleep    1s
#     END
#     Close Workbook

Open Web Magang
        open browser                     ${urlMagang}        ${browser}
        # Maximize Browser Window
        Set Window Size    1366    768
        sleep                            4s
        # SeleniumLibrary.element should be visible        ${FormLogin}       60s
        sleep    1s

# open web FMS user main
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read worksheet   browser   header=${TRUE}
#     FOR    ${row}    IN    @{data}
#         ${url}=           Set Variable    ${row}[url]   
#         ${browser}=       Set Variable    ${row}[browser] 
#         # Open browser
#         open browser                     ${url}        ${browser}
#         Maximize Browser Window
#         sleep                            4s
#         SeleniumLibrary.element should be visible        ${FormLogin}       60s
#         sleep    1s
#     END
#     Close Workbook

# Open Browser with url to view details
#     open browser                     https://astrafms-staging.trac.astra.co.id/settings/master-data/quality-check/view-form/2         ${browserused}    #${url}           #executable_path=C:/chromedriver/chrome_for_testing/chrome.exe
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}       60s
#     sleep                            1s

# Open Browser with url page update vehcyle
#     open browser                     https://astrafms-staging.trac.astra.co.id/vehicles/external/edit/44599         ${browserused}    #${url}           #executable_path=C:/chromedriver/chrome_for_testing/chrome.exe
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}       60s
#     sleep                            1s

# Open Browser with url create page vehcyle
#     open browser                     https://astrafms-staging.trac.astra.co.id/vehicles/external/add         ${browserused}    #${url}           #executable_path=C:/chromedriver/chrome_for_testing/chrome.exe
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}       60s
#     sleep                            1s

# Open Browser update form data DRBAC
#     open browser                     https://astrafms-staging.trac.astra.co.id/settings/master-data/quality-check/form/edit/10         ${browserused}    #${url}           #executable_path=C:/chromedriver/chrome_for_testing/chrome.exe
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}       60s
#     sleep                            1s

# Open Browser with url view details vehcyle
#     open browser                     https://astrafms-staging.trac.astra.co.id/vehicles/external/view/44599        ${browserused}    #${url}           #executable_path=C:/chromedriver/chrome_for_testing/chrome.exe
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}       60s
#     sleep                            1s

# Open Web FSM Sera Staging
#     open browser                     ${urlstaging}      ${browserused} 
#     Maximize Browser Window
#     sleep                            4s
#     element should be visible        ${FormLogin}
#     sleep                            1s

Close Browser Now
    Close Browser

# Login Web FMS
#      ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read Worksheet    user    header=${TRUE}
#     Close Workbook
#     ${row}=    Get From List    ${data}    0
#         ${login_type}=          Set Variable    ${row}[login_type]   
#         ${user1}=               Set Variable    ${row}[user1] 
#         ${user_mfa1}=           Set Variable    ${row}[mfa_account] 
#         ${password_local}=      Set Variable    ${row}[password_local] 
#         ${password_mfa}=        Set Variable    ${row}[password_mfa] 
#         ${otp_key}=             Set Variable    ${row}[otp_key] 
#         # Open browser
#         IF  '${login_type}' == 'LOCAL'
#                 input username or email sera        ${user1}
#                 input password valid sera           ${password_local}
#                 click button login with user local
#                 verify if user has been succesfully login

#         ELSE
#                 Click button login with MFA
#                 Input User ID MFA       ${user_mfa1}
#                 Input Password MFA      ${password_mfa}
#                 Get OTP from secret MFA and input to text field otp     ${otp_key}
#                 Click Button Submit
#                 verify if user has been succesfully login
#         END

# login web FMS with user main sera
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read Worksheet    user    header=${TRUE}
#     Close Workbook
#     ${row}=    Get From List    ${data}    1
#         ${login_type}=          Set Variable    ${row}[login_type]   
#         ${user1}=               Set Variable    ${row}[user1] 
#         ${user_mfa1}=           Set Variable    ${row}[mfa_account] 
#         ${password_local}=      Set Variable    ${row}[password_local] 
#         ${password_mfa}=        Set Variable    ${row}[password_mfa] 
#         ${otp_key}=             Set Variable    ${row}[otp_key] 
#         # Open browser
#         IF  '${login_type}' == 'LOCAL'
#                 input username or email sera        ${user1}
#                 input password valid sera           ${password_local}
#                 click button login with user local
#                 verify if user has been succesfully login

#         ELSE
#                 Click button login with MFA
#                 Input User ID MFA       ${user_mfa1}
#                 Input Password MFA      ${password_mfa}
#                 Get OTP from secret MFA and input to text field otp     ${otp_key}
#                 Click Button Submit
#                 verify if user has been succesfully login
#         END

# Login Web FMS with user FMS 3
#     input text                       ${Usernameweb}    ${ACCOUNT.emailfms3_${ENV}}
#     sleep                            1s
#     input text                       ${Passwordweb}    ${ACCOUNT.pass_${ENV}}
#     sleep                            1s
#     click element                    ${Buttonlgn}
#     sleep                            2s
#     Wait Until Element Is Visible    ${VerifyWebLogin}    60s
#     element should be visible        ${VerifyWebLogin}
#     sleep                            5s

# Login Web FSM with user FMS 3 for PBAC
#     input text                       ${Usernameweb}    ${ACCOUNT.emailfms3_${ENV}}
#     sleep                            1s
#     input text                       ${Passwordweb}    ${ACCOUNT.pass_${ENV}}
#     sleep                            1s
#     click element                    ${Buttonlgn}
#     sleep                            10s

# Login Web FMS with user FMS 1
#     input text                       ${Usernameweb}    ${ACCOUNT.email_${ENV}}
#     sleep                            1s
#     input text                       ${Passwordweb}    ${ACCOUNT.pass_${ENV}}
#     sleep                            1s
#     click element                    ${Buttonlgn}
#     sleep                            20s
    # Wait Until Element Is Visible    ${VerifyWebLogin}    60s
    # element should be visible        ${VerifyWebLogin}
    # sleep                            5s

# Login Web FSM with ${username_sera} and ${password_sera}
#     input username sera              ${username_sera}
#     input password sera              ${password_sera}
#     click element                    ${Buttonlgn}
#     sleep                            2s
#     Wait Until Element Is Visible    ${VerifyWebLogin}    60s
#     element should be visible        ${VerifyWebLogin}
#     sleep                            5s

# input username sera 
#     [Arguments]    ${username_sera}
#     input text                       ${Username}    ${username_sera}
#     sleep                            1s

# input password sera 
#     [Arguments]    ${password_sera}
#     input text                       ${Password}    ${password_sera}
#     sleep                            1s

# Login Web FMS User 3
#     input text                       ${Username}    ${loginuserfms3}
#     sleep                            1s
#     input text                       ${Password}    ${loginpass}  
#     sleep                            1s
#     click element                    ${Buttonlgn}
#     sleep                            2s
#     Wait Until Element Is Visible    ${VerifyWebLogin}    60s
#     element should be visible        ${VerifyWebLogin}
#     sleep                            5s

# Click button login with MFA
#     click element                    ${ButtonloginMFA}
#     Wait Until Element Is Visible    ${useridmfa}       60s
#     sleep                            2s

# Input User ID MFA
#     [Arguments]     ${user_mfa1}
#     input text                       ${useridmfa}    ${user_mfa1}
#     sleep                            1s
#     click element                    ${buttonnextid}
#     Wait Until Element Is Visible    ${passmfa}       60s
#     sleep                            2s

# Input Password MFA
#     [Arguments]     ${password_mfa}
#     input text                       ${passmfa}    ${password_mfa}
#     sleep                            1s
#     click element                    ${buttonsiginmfa}
#     sleep                            1s

# Get OTP from secret MFA and input to text field otp
#     [Arguments]     ${otp_key}
#     ${Statuselementotp}=  Run Keyword And Return Status  Element Should Be Visible  ${inputotp}
#     sleep                            1s
#     IF  ${Statuselementotp} == 'True'
#         Wait Until Element Is Visible    ${inputotp}       60s
#         sleep                            2s
#         ${otp}=    Get OTP    ${otp_key}
#         log   ${otp}
#         # Should Match Regexp       ${otp}        //d{6}
#         input text                ${inputotp}       ${otp}
#         sleep               2s
#         click element       ${buttonnext1}
#         sleep                            5s
#     ELSE
#         sleep                            1s
#     END

# Click Button Submit
#     click element                    ${btnsubmitmfa}
#     Wait Until Element Is Visible    ${VerifyWebLogin}    60s
#     Element should be visible        ${VerifyWebLogin}
#     sleep                            5s

# verify if user has been succesfully login
#     SeleniumLibrary.Wait Until Element Is Visible    ${VerifyWebLogin}    60s
#     SeleniumLibrary.element should be visible        ${VerifyWebLogin}
#     sleep                            8s

# input username or email sera
#     [Arguments]     ${user1}
#     SeleniumLibrary.input text                       ${Usernameweb}    ${user1}
#     sleep                            1s
    
# input password valid sera
#     [Arguments]     ${password_local}
#     SeleniumLibrary.input text                       ${Passwordweb}    ${password_local}
#     sleep                            1s

# click button login with user local
#     SeleniumLibrary.click element                    ${Buttonlgn}
#     sleep                            2s

# #login for drbac
# open browser with user not have drbac for view
#         ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read worksheet   browser   header=${TRUE}
#     FOR    ${row}    IN    @{data}
#         ${browser}=       Set Variable    ${row}[browser] 
#         # Open browser
#         open browser                     ${current_url}        ${browser}
#         # Maximize Browser Window
#         Set Window Size    1920    1080
#         sleep                            4s
#         SeleniumLibrary.element should be visible        ${FormLogin}       60s
#         sleep    1s
#     END
#     Close Workbook

# open browser with user not have drbac for update
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read worksheet   browser   header=${TRUE}
#     FOR    ${row}    IN    @{data}
#         ${browser}=       Set Variable    ${row}[browser] 
#         # Open browser
#         open browser                     ${current_url2}       ${browser}
#         # Maximize Browser Window
#         Set Window Size    1920    1080
#         sleep                            4s
#         SeleniumLibrary.element should be visible        ${FormLogin}       60s
#         sleep    1s
#     END

# get data from excel and login
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read Worksheet    user    header=${TRUE}
#     Close Workbook
#     ${second_row}=    Get From List    ${data}    1
#     Log    ${second_row}
#     ${user1}=                    Set Variable    ${second_row}[user1]
#     ${password_local}=           Set Variable    ${second_row}[password_local]
#     #Login with user not have drbac
#     input username or email sera    ${user1}
#     input password valid sera       ${password_local}
#     click button login with user local
#     sleep  10s

# get data from excel and login for main fms
#     ${workbook}=    Open Workbook    ${CURDIR}/../../testScenario/loginUser/sourceData/login_${ENV}.xlsx
#     @{data}=    Read Worksheet    user    header=${TRUE}
#     Close Workbook
#     ${second_row}=    Get From List    ${data}    0
#     Log    ${second_row}
#     ${user1}=                    Set Variable    ${second_row}[user1]
#     ${password_local}=           Set Variable    ${second_row}[password_local]
#     #Login with user not have drbac
#     input username or email sera    ${user1}
#     input password valid sera       ${password_local}
#     click button login with user local
#     sleep  10s
