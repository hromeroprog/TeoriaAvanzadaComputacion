# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 13:51:10 2021

@author: 2rome
"""

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
import math
from scipy import stats

def abline(slope, intercept, r_value):
    """Plot a line from slope and intercept"""
    axes = plt.gca()
    x_vals = np.array(axes.get_xlim())
    y_vals = intercept + slope * x_vals
    plt.plot(x_vals, y_vals, '--', color= 'gray')
    axes.text(0*10**8, 1.5*10**8, f"Tiempo = {round(intercept, 3)} + {round(slope, 12)} * Número\nR^2 = {round(r_value, 4)}", horizontalalignment='left', size='medium', color='dimgrey', weight='normal')
    

folder = "primality_files"
filename = "improved_1_primes"
mode = 3

titulo1= "Número evaluado frente a tiempo de ejecución"
titulo2= "Número evaluado frente a tiempo de ejecución (solo primos)"
titulo3= "Tamaño de número evaluado frente a tiempo de ejecución"
titulo4= "Tamaño de número evaluado frente a tiempo de ejecución (solo primos)"

if mode < 2:
   eje_x= "Number"
else:
    eje_x = "Tamaño"

print(f"reading: ./{folder}/{filename}.csv")
df  = pd.read_csv(f"./{folder}/{filename}.csv")

df.isPrime = ["true" if boolean else "false" for boolean in df.isPrime]
df['Tamaño'] = [math.log(x,10) for x in df.Number]
#df = sns.load_dataset("data.csv")
df.Time = [math.log(x) if x>0 else None for x in df.Time]
#df.Time = [x**2 for x in df.Time]
#df['Number'] = [x**2 for x in df.Number]

slope, intercept, r_value, p_value, std_err = stats.linregress(df[eje_x],df.Time)

 
# Use the 'hue' argument to provide a factor variable
graph = sns.lmplot( x=eje_x, y="Time", data=df, fit_reg=False, ci = None, hue='isPrime',palette = dict(true="red",false="blue"), legend=True, scatter_kws={"s": 6}, height = 6, aspect = 1.5)

# Move the legend to an empty part of the plot
#plt.legend(loc='lower right')

graph.set(xscale="linear", yscale="linear")

sns.set_style('white')
sns.set_style("ticks")

graph._legend.set_title("Primalidad")
# replace labels
new_labels = ['Compuestos', 'Primos']
if mode%2 == 1:
    new_labels = ['Primos', 'Primos']
    
for t, l in zip(graph._legend.texts, new_labels):
    t.set_text(l)


titulos = [titulo1, titulo2, titulo3, titulo4]

#abline(slope, intercept, r_value)

plt.title(titulos[mode] + "\n\n")
plt.ylabel("Log base 10 de Tiempo (ms)")

if mode < 2:
    plt.xlabel("Número")
else:
    plt.xlabel("Tamaño de número")

plt.ticklabel_format(axis='x',style = "sci", useMathText=True)
plt.ticklabel_format(axis='y',style = "sci", useMathText=True)

plt.show()
