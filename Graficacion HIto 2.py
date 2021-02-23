# -*- coding: utf-8 -*-
"""
Created on Mon Feb 22 18:56:36 2021

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
    x_vals[0] = -intercept/slope
    y_vals = intercept + slope * x_vals
    print(x_vals)
    print(y_vals)
    
    plt.plot(x_vals, y_vals, '--', color= 'gray')
    axes.text(2, 3, f"Tiempo = {round(intercept, 3)} + {round(slope, 4)} * Número\nR^2 = {round(r_value, 6)}", horizontalalignment='left', size='medium', color='dimgrey', weight='normal')
    

folder = "reduction_files"
filename = "reduccion_primes"
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

#df.isPrime = ["true" if boolean else "false" for boolean in df.isPrime]
df['Tamaño'] = [math.log(x,10) for x in df.Number]
#df = sns.load_dataset("data.csv")

#df = df[df['Time'] > 0.1]
print(df)
df.Time = [math.log(x, 10) if x>0 else None for x in df.Time]
#df.Time = [x**2 for x in df.Time]
#df['Number'] = [x**2 for x in df.Number]

slope, intercept, r_value, p_value, std_err = stats.linregress(df[df.Time > 0][eje_x],df[df.Time > 0]['Time'])

# Use the 'hue' argument to provide a factor variable
graph = sns.lmplot( x=eje_x, y="Time", data=df, fit_reg=False, ci = None, legend=True, scatter_kws={"s": 6}, height = 6, aspect = 1.5)

# Move the legend to an empty part of the plot
#plt.legend(loc='lower right')

graph.set(xscale="linear", yscale="linear")

sns.set_style('white')
sns.set_style("ticks")


titulos = [titulo1, titulo2, titulo3, titulo4]

abline(slope, intercept, r_value)

plt.title(titulos[mode] + "\n\n")
plt.ylabel("Log base 10 de Tiempo (ms)")

if mode < 2:
    plt.xlabel("Número")
else:
    plt.xlabel("Tamaño de número")

plt.ticklabel_format(axis='x',style = "sci", useMathText=True)
plt.ticklabel_format(axis='y',style = "sci", useMathText=True)

plt.show()