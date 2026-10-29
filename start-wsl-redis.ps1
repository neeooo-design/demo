$ErrorActionPreference = 'Stop'

# Keep WSL alive so Windows localhost forwarding remains available.
$helper = Get-CimInstance Win32_Process -Filter "Name='wsl.exe'" | Where-Object {
    $_.CommandLine -match '-d Ubuntu -- sleep infinity'
}
if (-not $helper) {
    Start-Process -FilePath 'wsl.exe' -ArgumentList '-d Ubuntu -- sleep infinity' -WindowStyle Hidden | Out-Null
}
& wsl.exe -d Ubuntu -u root -- systemctl start redis-server
if ($LASTEXITCODE -ne 0) { throw 'Failed to start WSL Redis service' }

for ($attempt = 0; $attempt -lt 20; $attempt++) {
    $client = [System.Net.Sockets.TcpClient]::new()
    try {
        $client.Connect('127.0.0.1', 6379)
        $stream = $client.GetStream()
        $stream.ReadTimeout = 2000
        $request = [System.Text.Encoding]::UTF8.GetBytes("PING`r`n")
        $stream.Write($request, 0, $request.Length)
        $buffer = [byte[]]::new(128)
        $count = $stream.Read($buffer, 0, $buffer.Length)
        if ([System.Text.Encoding]::UTF8.GetString($buffer, 0, $count).StartsWith('+PONG')) {
            Write-Output 'WSL Redis ready at 127.0.0.1:6379'
            exit 0
        }
    } catch {
        # WSL may need a few seconds to initialize localhost forwarding.
    } finally {
        $client.Dispose()
    }
    Start-Sleep -Milliseconds 500
}
throw 'WSL Redis did not become accessible on localhost:6379'
