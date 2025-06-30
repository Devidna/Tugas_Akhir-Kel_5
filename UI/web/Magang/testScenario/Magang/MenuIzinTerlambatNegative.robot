*** Settings ***
Library         SeleniumLibrary
Library         ScreenCapLibrary
Resource        ${CURDIR}/../../resources/steps/Loginuser.robot
Resource        ${CURDIR}/../../resources/steps/Laporan_Izin_Terlambat/laporanIzinTerlambatNegative.robot

*** Test Cases ***
Pencarian Nama User Tidak Terdaftar
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatNegative
    Given Open Web Magang
        Start video Record Laporan Izin Terlambat Negative
        And Login On Web Magang
        And Menu Laporan Terlambat
    When Pencarian Data Nama User Tidak Terdaftar
        And Klik tombol pencarian Pencarian Nama
    Then Validasi Pencarian Data User Tidak Terdaftar
Pencarian Nama Unit Tidak Terdaftar
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatNegative
    # rolesetting.Start Video Create User non sa to sa
    Given Pencarian Data Nama User Tidak Terdaftar
        And Pencarian Data Menggunakan Filter Unit Tidak Terdaftar
    When Klik Tombol Terapkan
        And Klik tombol pencarian Pencarian Unit Tidak Terdaftar
    Then Validasi Filter Data Unit Tidak Terdaftar
Pencarian Filter Tanggal Tidak Terdaftar
    [documentation]  Super Admin Flag
    [Tags]  LaporanIjinTerlambatNegative
    # rolesetting.Start Video Create User non sa to sa
    Given Pencarian Data menggunakan Filter Tanggal
        And Klik Tombol Save Tanggal
        And Klik Tombol Pencarian Tanggal
    Then Validasi Pencarian Filter Tanggal Tidak Terdaftar
    Stop Video Record
    
    



    