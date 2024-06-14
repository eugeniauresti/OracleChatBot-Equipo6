from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time
import json
import requests
import urllib.parse
import xlsxwriter

reporte = xlsxwriter.Workbook("Reporte de Pruebas Automatizadas de endpoints.xlsx")
repo1 = reporte.add_worksheet()
repo1.write('A1', 'Nombre de Endpoint Probado')
repo1.write('B1', 'Resultado de Prueba')

driver = webdriver.Chrome()
driver.implicitly_wait(1)
driver.maximize_window()
driver.get("http://159.54.130.53")#IP externa del balancador de cargas
usersID = ["/usuarioPorId/1","/usuarioPorId/2","/usuarioPorId/3","/usuarioPorId/4","/usuarioPorId/5","/usuarioPorId/6","/usuarioPorId/7","/usuarioPorId/8"]
telegramID = ["/telegramId/6012953505","/telegramId/6009001345","/telegramId/5347850845","/telegramId/6906514151","/telegramId/9563487921","/telegramId/3482765409","/telegramId/9427519983","/telegramId/7186088329"]
tareaID = ["/listaTarea/43","/listaTarea/41","/listaTarea/42","/listaTarea/50","/listaTarea/51","/listaTarea/52","/listaTarea/53","/listaTarea/17","/listaTarea/64","/listaTarea/44","/listaTarea/45","/listaTarea/46","/listaTarea/47","/listaTarea/48","/listaTarea/49","/listaTarea/28","/listaTarea/55","/listaTarea/56","/listaTarea/57","/listaTarea/81","/listaTarea/82","/listaTarea/101"]
endpoints = ["listaTarea", "usuarios","usuarioPorId/1","usuarioPorId/2","usuarioPorId/3","usuarioPorId/4","usuarioPorId/5","usuarioPorId/6","usuarioPorId/7","usuarioPorId/8","listaTarea/43","listaTarea/41","listaTarea/42","listaTarea/50","listaTarea/51","listaTarea/52","listaTarea/53","listaTarea/17","listaTarea/64","listaTarea/44","listaTarea/45","listaTarea/46","listaTarea/47","listaTarea/48","listaTarea/49","listaTarea/28","listaTarea/55","listaTarea/56","listaTarea/57","listaTarea/81","listaTarea/82","listaTarea/101"]
fails = 0
success = 0
total = 0
for x in endpoints:
    urlapi = driver.current_url + x
    response = requests.get(urlapi)
    if response.status_code == 200:
        print(urlapi + "  Endpoint pasa la prueba\n")
        success = success +1
        repo1.write("A"+str(total+2), urlapi)
        repo1.write("B"+str(total+2), "Endpoint pasa la prueba")
    else:
        print(urlapi + "  Endpoint no pasa la prueba\n")
        fails = fails +1
        repo1.write("A"+str(total+2), urlapi)
        repo1.write("B"+str(total+2), "Endpoint no pasa la prueba")
    total = total +1

print("En total " +str(success)+" de "+str(total)+ " pruebas de endpoints fueron exitosas\n")
print("En total " +str(fails)+" de "+str(total)+ " pruebas de endpoints fueron exitosas\n")
repo1.write('D2', 'Pruebas Exitosas')
repo1.write('D3', 'Pruebas Fallidas')
repo1.write('D4', 'Pruebas Totales')
repo1.write('E2', str(success))
repo1.write('E3', str(fails))
repo1.write('E4', str(total))
reporte.close()