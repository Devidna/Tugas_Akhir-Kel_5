*** Settings ***
Library         SeleniumLibrary
Library         ScreenCapLibrary
Resource        ${CURDIR}/../../resources/steps/Loginuser.robot
Resource        ${CURDIR}/../../resources/steps/Laporan_Izin_Terlambat/laporanIzinTerlambatPositif.robot

*** Test Cases ***
Pencarian Nama User Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatPositive
    Given Open Web Magang
    Start video Record Laporan Izin Terlambat Positive
        And Login On Web Magang
        And Menu Laporan Terlambat
        And Pencarian Data Nama User
    When Klik tombol pencarian
    Then Validasi Pencarian Data User
Pencarian Filter Unit Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatPositive
    Given Pencarian Data Nama User
        And Pencarian Data Menggunakan Filter Unit
        And Klik Tombol Terapkan
    When Klik tombol pencarian
    Then Validasi Filter Data Unit
Pencarian Filter Tanggal Terdaftar(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatPositive
    Given Reset Pencarian Data Laporan Izin Pulang Cepat
        And Pencarian Data menggunakan Filter Tanggal
        And Klik Tombol Save Tanggal
        And Klik Tombol Pencarian Tanggal
    Then Validasi Pencarian Filter Tanggal
Pencarian Data Laporan Izin Pulang Terlambat(+)
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatPositive
    Given Reset Pencarian Data Laporan Izin Pulang Cepat
        And Pencarian Data Nama User
        And Pencarian Data Menggunakan Filter Unit
        And Klik Tombol Terapkan
    When Pencarian Data menggunakan Filter Tanggal
        And Klik Tombol Save Tanggal
        And Klik Tombol Pencarian Tanggal
    Then Validasi Tanggal Terdaftar Izin Pulang Cepat
    Stop Video Record
        


    