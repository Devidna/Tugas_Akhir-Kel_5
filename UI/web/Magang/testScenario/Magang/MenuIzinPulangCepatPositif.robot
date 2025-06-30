*** Settings ***
Library         SeleniumLibrary
Library         ScreenCapLibrary
Resource        ${CURDIR}/../../resources/steps/Loginuser.robot
Resource        ${CURDIR}/../../resources/steps/Laporan_Izin_Pulang_Cepat/laporanIzinPulangCepatPositif.robot

*** Test Cases ***
Pencarian Nama User Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanPulangCepatPositif
    # rolesetting.Start Video Create User non sa to sa
    Given Open Web Magang
        And Start video Record Laporan Izin Pulang Cepat Positive
        And Login On Web Magang
        And Menu Laporan Ijin Pulang Cepat
        And Pencarian Data Nama User Izin Pulang Cepat
    When Klik tombol pencarian
    Then Validasi Pencarian Data User
Pencarian Filter Unit Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanPulangCepatPositif
    # rolesetting.Start Video Create User non sa to sa
    Given Pencarian Data Nama User Izin Pulang Cepat
        And Pencarian Data Menggunakan Filter Unit Izin Pulang Cepat
        And Klik Tombol Terapkan
    When Klik tombol pencarian
    Then Validasi Filter Data Unit
Pencarian Filter Tanggal Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanPulangCepatPositif
    # rolesetting.Start Video Create User non sa to sa
    Given Reset Pencarian Data Laporan Izin Pulang Cepat
        And Pencarian Data Izin Pulang Cepat menggunakan Filter Tanggal
        And Klik Tombol Save Tanggal
        And Klik Tombol Pencarian Tanggal
    Then Validasi Pencarian Filter Tanggal
Pencarian Data Laporan Izin Pulang Cepat(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanPulangCepatPositif
    Given Reset Pencarian Data Laporan Izin Pulang Cepat
        And Pencarian Data Nama User Izin Pulang Cepat
        And Pencarian Data Menggunakan Filter Unit Izin Pulang Cepat
        And Klik Tombol Terapkan
    When Pencarian Data Izin Pulang Cepat menggunakan Filter Tanggal
        And Klik Tombol Save Tanggal
        And Klik Tombol Pencarian Tanggal
    Then Validasi Tanggal Terdaftar Izin Pulang Cepat
    Stop Video Record