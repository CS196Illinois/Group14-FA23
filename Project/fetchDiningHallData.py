"""Basic implementation of fetching menu data in python"""
import requests

URL = "https://web.housing.illinois.edu/DiningMenus/api/DiningMenu/GetOption/"
"""
    DiningOptionID: 
        LAR Field of Greens: 12
        Ikenberry Dining Hall: 1
        ISR Dining Hall: 3
        LAR Kosher Kitchen: 23
        LAR Dining Hall: 5
        Par Dining Hall: 2
    mealDate: "YY-MM-DD" (sends breakfast, lunch, dinner all together)
"""
data = {"DiningOptionID": "2", "mealDate": "2023-10-01"}
try: 
    out = requests.post(URL, json = data, timeout=5)
except requests.exceptions.Timeout:
    print("API took too long to respond")

print(out.text)
