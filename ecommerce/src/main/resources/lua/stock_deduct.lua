local stock = tonumber(redis.call('get', KEYS[1]))
if stock == nil then
    return -1
end
if stock <= 0 then
    return 0
end
local newStock = redis.call('decr', KEYS[1])
return newStock
