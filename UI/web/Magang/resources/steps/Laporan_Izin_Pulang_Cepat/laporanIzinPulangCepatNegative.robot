*** Settings ***
Library         SeleniumLibrary     run_on_failure=None
# Library         RPA.Excel.Files
Library         ScreenCapLibrary
Variables       ${CURDIR}/../../locator/elementLaporanIjinPulangCepat.py 
Library         ${CURDIR}/../../helper/scrollDown.py


*** Keywords ***
#LoginMagang
Login On Web Magang
    SeleniumLibrary.Click Element    ${email}
    SeleniumLibrary.Input Text    ${email}    admin@hadir.com
    SeleniumLibrary.Click Element    ${password}
    SeleniumLibrary.Input Text    ${password}    MagangSQA_JC@123
    #click tombol login 
    SeleniumLibrary.Click Button    ${tombolMasuk}
    sleep       3s   
Klik Menu Ijin Pulang Cepat 
        SeleniumLibrary.Wait Until Element Is Enabled    ${menulaporan}
    SeleniumLibrary.Click Element    ${menulaporan}
    sleep                   3s
    SeleniumLibrary.Click Element    ${menuIzinPulangCepat}
    sleep                   3s
Pencarian Data Nama User 
    sleep                    2s
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    Qwerty
    sleep                    4s
Validasi Pencarian Data User Tidak Terdaftar
    SeleniumLibrary.Element Should Be Enabled    ${validasiPencarianDataTidakTerdaftar}
    sleep                    3s
    SeleniumLibrary.Click Button    ${resetTombol}
Klik tombol pencarian Pencarian Nama
    sleep                    3s
    SeleniumLibrary.Click Button    ${clickTombolSearch2}
Pencarian Data Nama User Tidak Terdaftar 
    SeleniumLibrary.Wait Until Element Is Enabled    ${cariNamaUser}
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    Qwerty
    sleep                    4s
Pencarian Data Menggunakan Filter Unit Tidak Terdaftar
    SeleniumLibrary.Click Button    ${filterDataUnit}
    SeleniumLibrary.Click Element    ${cariDataUnit}
    SeleniumLibrary.Input Text    ${cariDataUnit}    Qwerty
    sleep                    3s
Klik Tombol Terapkan
    SeleniumLibrary.Click Button    ${klikTombolTerapkan}
    sleep                    3s
Klik tombol pencarian Pencarian Unit Tidak Terdaftar
    sleep                    3s
    SeleniumLibrary.Click Button    ${clickTombolSearch2}
Validasi Filter Data Unit Tidak Terdaftar
    SeleniumLibrary.Element Should Be Enabled    ${validasiFilterDataUnit}
    sleep                    3s
Pencarian Data menggunakan Filter Tanggal
    SeleniumLibrary.Click Element    ${klikSstartDate}
    sleep                    3s
    SeleniumLibrary.Click Element    ${pilihStartDate}
    sleep                    3s
    SeleniumLibrary.Click Element    ${pilihStartDate}
    #Scroll Down
    SeleniumLibrary.Scroll Element Into View    ${scrollDownFilterDate}
    sleep                    5s
Klik Tombol Save Tanggal
    SeleniumLibrary.Click Button    ${tombolSaveStartDate}
    sleep                    3s
    SeleniumLibrary.Scroll Element Into View    ${clickTombolSearch2}
    sleep                    4s
Klik Tombol Pencarian Tanggal
    SeleniumLibrary.Click Button    ${clickTombolSearch2}
    sleep                    4s
Validasi Pencarian Filter Tanggal Tidak Terdaftar
    sleep                     3s
    SeleniumLibrary.Element Should Be Enabled    ${validasiFilterDateTidakTerdaftar}
Start video Record Laporan Izin Pulang Cepat Negative
    Start Video Recording   alias=None   name=duplicatesubcategory  fps=None   size_percentage=1  embed=True  embed_width=100px  monitor=1
Stop Video Record
    Stop Video Recording    alias=None 