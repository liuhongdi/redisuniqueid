local id_key = 'id_key_'..KEYS[1]
local current = redis.call('get',id_key)
if current == false then
    redis.call('set',id_key,1)
    --redis.log(redis.LOG_NOTICE,' isset1:'..isset..':')
    return '1'
end
redis.log(redis.LOG_NOTICE,' current:'..current..':')
local result =  tonumber(current)+1
redis.log(redis.LOG_NOTICE,' result:'..result..':')
redis.call('set',id_key,result)
return tostring(result)