*** Settings ***
Library         SeleniumLibrary     run_on_failure=None
# Library         RPA.Excel.Files
Library         ScreenCapLibrary
Variables       ${CURDIR}/../../locator/elementLaporanIjinPulangCepat.py 
Library         ${CURDIR}/../../helper/scrollDown.py

*** Keywords ***
Login On Web Magang
    SeleniumLibrary.Click Element    ${email}
    SeleniumLibrary.Input Text    ${email}    admin@hadir.com
    SeleniumLibrary.Click Element    ${password}
    SeleniumLibrary.Input Text    ${password}    MagangSQA_JC@123
    #click tombol login 
    SeleniumLibrary.Click Button    ${tombolMasuk}
    sleep                   4s

Menu Laporan Ijin Pulang Cepat
    SeleniumLibrary.Wait Until Element Is Enabled    ${menulaporan}
    SeleniumLibrary.Click Element    ${menulaporan}
    sleep                   3s
    SeleniumLibrary.Click Element    ${menuIzinPulangCepat}
    sleep                   3s

Pencarian Data Nama User 
    sleep                    2s
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    komar
    sleep                    4s
Pencarian Data Nama User Izin Pulang Cepat 
    sleep                    4s
    SeleniumLibrary.Click Element    ${cariNamaUser}
    SeleniumLibrary.Input Text    ${cariNamaUser}    juned
    sleep                    4s
Klik tombol pencarian
    SeleniumLibrary.Click Button    ${clickTombolSearch2}
Validasi Pencarian Data User
    SeleniumLibrary.Element Should Be Enabled    ${ValidasiPencarianData}
    sleep                    3s
    SeleniumLibrary.Click Button    ${resetTombol}
Reset Pencarian Data Laporan Izin Pulang Cepat
    SeleniumLibrary.Click Button    ${resetTombol}

Pencarian Data Menggunakan Filter Unit
    SeleniumLibrary.Click Button    ${filterDataUnit}
    SeleniumLibrary.Click Element    ${cariDataUnit}
    SeleniumLibrary.Input Text    ${cariDataUnit}    EDC Nasional
    sleep                    5s
Pencarian Data Menggunakan Filter Unit Izin Pulang Cepat
    SeleniumLibrary.Click Button    ${filterDataUnit}
    SeleniumLibrary.Click Element    ${cariDataUnit}
    SeleniumLibrary.Input Text    ${cariDataUnit}    hadirsqa27
    sleep                    5s
Klik Tombol Terapkan
    SeleniumLibrary.Click Button    ${klikTombolTerapkan}
    sleep                    3s
Validasi Filter Data Unit
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
Pencarian Data Izin Pulang Cepat menggunakan Filter Tanggal
    SeleniumLibrary.Click Element    ${klikSstartDate}
    sleep                    3s
    SeleniumLibrary.Click Element    ${setTanggalKembali}
    sleep                    2s
    SeleniumLibrary.Click Element    ${setTanggalKembali}
    sleep                    2s
    SeleniumLibrary.Click Element    ${setTanggalKembali}
    sleep                    2s
    SeleniumLibrary.Click Element    ${setFilterTanggal}
    sleep                    2s
    SeleniumLibrary.Click Element    ${setFilterTanggal}
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
Validasi Pencarian Filter Tanggal
    SeleniumLibrary.Element Should Be Enabled    ${validasiPencarianFilterDate}
Validasi Filter Data Unit Tidak Terdaftar
    SeleniumLibrary.Element Should Be Enabled    ${validasiFilterDataUnit}
    sleep                    3s
Pencarian Data menggunakan Filter Tanggal Tidak Terdaftar
    SeleniumLibrary.Click Element    ${klikSstartDate}
    sleep                    3s
    SeleniumLibrary.Click Element    ${pilihStartDate}
    sleep                    3s
    SeleniumLibrary.Click Element    ${pilihStartDate}
    #Scroll Down
    SeleniumLibrary.Scroll Element Into View    ${scrollDownFilterDate}
    sleep                    5s
Validasi Tanggal Terdaftar Izin Pulang Cepat
    sleep                    3s
    SeleniumLibrary.Element Should Be Enabled    ${validasiPencarianDataTerdaftar}
Start video Record Laporan Izin Pulang Cepat Positive
    Start Video Recording   alias=None   name=duplicatesubcategory  fps=None   size_percentage=1  embed=True  embed_width=100px  monitor=1
Stop Video Record
    Stop Video Recording    alias=None 