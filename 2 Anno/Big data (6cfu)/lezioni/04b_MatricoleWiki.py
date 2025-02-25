import requests
from bs4 import BeautifulSoup

url = 'https://it.wikipedia.org/wiki/Classifica_delle_Università_Italiane_per_numero_di_studenti'
response = requests.get(url)
soup = BeautifulSoup(response.text, 'html.parser')

table = soup.find('table', class_='wikitable sortable')
rows = table.find_all('tr')

for row in rows[1:]:
    cells = row.find_all('td')
    rank = cells[0].get_text().strip()
    name = cells[1].get_text().strip()
    students = cells[2].get_text().strip()
    city = cells[3].get_text().strip()
    num_stud = cells[4].get_text().strip()
    print(rank, name, students, city, num_stud)
