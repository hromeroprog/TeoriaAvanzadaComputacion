# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""
from parser import Generador_Escenario

if __name__ == '__main__':
    escenario = Generador_Escenario()
    fichero = "ulysses16.tsp"
    escenario.obtener_desde_archivo_tsp(fichero)

