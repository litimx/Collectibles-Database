import fetch from 'node-fetch';

//API URLs
const priceChartingApiProductUrl = 'https://www.pricecharting.com/api/product';
const priceChartingApiProductsUrl = 'https://www.pricecharting.com/api/products';
const priceChartingOfferApiUrl = 'https://www.pricecharting.com/api/offers';

//API key
const apiToken = process.env.REACT_APP_API_TOKEN;;

export async function fetchItemById(itemId: string): Promise<any> {
  const response = await fetch(`${priceChartingApiProductUrl}?t=${apiToken}&id=${itemId}`);
  const data = await response.json();
  return data;
}

export async function fetchProductsByKeyword(keyword: string): Promise<any> {
  const response = await fetch(`${priceChartingApiProductsUrl}?t=${apiToken}&q=${keyword}`);
  const data = await response.json();
  return data;
}

export async function fetchSoldItems(itemId: string): Promise<any> {
  const response = await fetch(`${priceChartingOfferApiUrl}?t=${apiToken}&id=${itemId}&status=sold`);
  const data = await response.json();
  return data;
}

