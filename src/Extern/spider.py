import re
import urllib.request
import urllib.parse
import json

url = "https://catalog.ustc.edu.cn/api/teach/lesson/list-for-teach/341?access_token" \
      "=OWZhYzQ2OTI0OGYwZjczZWFkNGRhZThjM2ZlZmRkODU2NDg4ZWY0NGM3ZDYxMzUyYTkyMTVkY2Q4YWQwOGM0NA"

headers = {
    'Cookie': '_ga=GA1.1.710857031.1696834866; _ga_F7H0WYZZTK=GS1.1.1697002816.2.0.1697002816.0.0.0; '
              'sduuid=f960641e49acf41dbb2a400f15f6b127; uname=PB22061222; lv=1; fid=41967; '
              '41967userinfo'
              '=bd47f4dce1b38171ed12f71a42dec759c49d67c0c30ca5047c5a963e85f11099f039f4dddc9d52ce959649b496e5ba474cd1e3e'
              '47f3a6194afba7630f0da9b54; 41967UID=237241156; 41967enc=9C5E03BBA5FB79FE631C955B83D088FA; '
              '_uid=237241156; UID=237241156; vc=9C5E03BBA5FB79FE631C955B83D088FA; '
              'vc2=5911E46B7DB45ADA34FB91263A4B90DF; xxtenc=d26d28f07516cfefea7874ac94f2f248; '
              'uf=d9387224d3a6095b2900856f704c7131618cedda537394c9cefb3f70b4c4214772be33d0a78b2d220dbd3242f22d051b428b5'
              'a98cdb27be888b83130e7eb4704f266005c1959c8c473c7a207dbecc5838487ca4cea3b44019f46e7e86f1820ed1aaba7e8e30ca'
              '09ee7fafd565af53bf2; _d=1709366929245; '
              'vc3=c8FZ9X3DgJZMDD4609v8nKfhvPF12%2F6pajBNfaHRugfqimVhKPjzEIaP5KJcCjOizKc8H3fE'
              '%2Fa3atgtPyTiHAsf9HWDzlz0Lg69G5AWkcohd4sBGChgLvPcp'
              '%2Bo9vou6n4enp00I25pr0ervgPRi9u2bDpVRPZ9W71jhKgIja6eo%3Dd0f03c7f513dabdbde360811348bf5d7; '
              'cx_p_token=352da797ad40e8563816507bdad6e05d; '
              'p_auth_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9'
              '.eyJ1aWQiOiIyMzcyNDExNTYiLCJsb2dpblRpbWUiOjE3MDkzNjY5MjkyNDYsImV4cCI6MTcwOTk3MTcyOX0'
              '.gjln1M9vhERBtAaie2Nl6mLbp_9QeIB84Lg4xT9uB2c; DSSTASH_LOG=C_38-UN_17878-US_237241156-T_1709366929247',
    'Referer': 'https://catalog.ustc.edu.cn/query/lesson',
    'Sec-Ch-Ua': '"Chromium";v="122", "Not(A:Brand";v="24", "Microsoft Edge";v="122"',
    'Sec-Ch-Ua-Mobile': '?0',
    'Sec-Ch-Ua-Platform': '"Windows"',
    'Sec-Fetch-Dest': 'empty',
    'Sec-Fetch-Mode': 'cors',
    'Sec-Fetch-Site': 'same-origin',
    'Sec-Fetch-User': '?1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 '
                  'Safari/537.36 Edg/122.0.0.0'
}


request = urllib.request.Request(url, headers=headers)
response = urllib.request.urlopen(request)
data = response.read().decode('utf-8')
data = str(json.loads(data))


with open('page.html', 'w', encoding='utf-8') as file:
    file.write(data)

courses = re.findall("[{].*[}]", data)
print(courses)
