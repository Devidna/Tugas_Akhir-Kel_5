*** Settings ***
Library         SeleniumLibrary     run_on_failure=None
# Library         RPA.Excel.Files
Library         ScreenCapLibrary
Variables       ${CURDIR}/../../locator/elementLaporanIjinTerlambat.py 
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
    sleep                   6s
Menu Laporan Terlambat
    sleep                   4s
    SeleniumLibrary.Click Element    ${menulaporan}
    sleep                   3s
    SeleniumLibrary.Click Element    ${menuLaporanTerlambat}
    sleep                   3s

Pencarian Data Nama User Tidak Terdaftar 
    SeleniumLibrary.Wait Until Element Is Enabled    ${cariNamaUser}
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    Qwerty
    sleep                    4s
Klik tombol pencarian
    SeleniumLibrary.Click Button    ${clickTombolSearch}

Validasi Pencarian Data User Tidak Terdaftar
    SeleniumLibrary.Element Should Be Enabled    ${validasiPencarianDataTidakTerdaftar}
    sleep                    3s
    SeleniumLibrary.Click Button    ${resetTombol}

Pencarian Data Nama User 
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    komar
    sleep                    4s
Klik tombol pencarian Pencarian Nama
    sleep                    3s
    SeleniumLibrary.Click Button    ${clickTombolSearch}
Klik tombol pencarian Pencarian Unit Tidak Terdaftar
    sleep                    3s
    SeleniumLibrary.Click Button    ${clickTombolSearch}
Validasi Pencarian Data User
    SeleniumLibrary.Element Should Be Enabled    ${ValidasiPencarianData}
    sleep                    3s
    SeleniumLibrary.Click Button    ${resetTombol}
Pencarian Data Menggunakan Filter Unit Tidak Terdaftar
    SeleniumLibrary.Click Button    ${filterDataUnit}
    SeleniumLibrary.Click Element    ${cariDataUnit}
    SeleniumLibrary.Input Text    ${cariDataUnit}    Qwerty
    sleep                    5s
Klik Tombol Terapkan
    SeleniumLibrary.Click Button    ${klikTombolTerapkan}
    sleep                    3s
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
    SeleniumLibrary.Scroll Element Into View    ${clickTombolSearch}
    sleep                    4s
Klik Tombol Pencarian Tanggal
    SeleniumLibrary.Click Button    ${clickTombolSearch}
    sleep                    4s
Validasi Pencarian Filter Tanggal Tidak Terdaftar
    sleep                     3s
    SeleniumLibrary.Element Should Be Enabled    ${validasiFilterDateTidakTerdaftar}
Start video Record Laporan Izin Terlambat Negative
    Start Video Recording   alias=None   name=duplicatesubcategory  fps=None   size_percentage=1  embed=True  embed_width=100px  monitor=1
Stop Video Record
    Stop Video Recording    alias=None 