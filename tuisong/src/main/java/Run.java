import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Run {
    //读取json文件
    public static String readJsonFile() {
        String jsonStr = "";
        try {
            File jsonFile = new File("F:\\myProject\\gitAndProgram\\tuisong\\src\\main\\resources\\1.json");
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        String object = readJsonFile();
        List<ProjectInfo> projectInfos = JSONArray.parseArray(object, ProjectInfo.class);
        Map<String, List<ProjectInfo>> projectMap = projectInfos.stream().collect(Collectors.groupingBy(e -> e.getProjectGuid()));
        Map<String, List<String>> bidItemGuidMap = new HashMap<>();
        for (String projectGuId : projectMap.keySet()) {
            List<String> bidItemGuIds = projectMap.get(projectGuId).stream().map(ProjectInfo::getBidItemGuid).collect(Collectors.toList());
            bidItemGuidMap.put(projectGuId, bidItemGuIds);
        }
        System.out.println(JSON.toJSONString(projectInfos));
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyU3RhdHVzIjoiMSIsImxvZ2luVHlwZSI6MSwidXNlcl9uYW1lIjoi5YiY5YuD6ZizIiwidXNlclR5cGVJbmZvcyI6W3sidXNlclR5cGVJZCI6IjMiLCJjb21tb25UeXBlIjoiMyIsInN5c3RlbVR5cGUiOiIzIiwic3RhdHVzIjoiMSJ9XSwidXNlck5hbWUiOiLliJjli4PpmLMiLCJ1c2VySWQiOiJDRUUwNDZGNUU3NTQ0Q0M4OUY0ODkzQTRDOTdCNDUxNiIsImNsaWVudF9pZCI6ImdwLWdhdGV3YXktY2VudGVyIiwiYXVkIjpbIkFMTCJdLCJpZGVudGl0eVR5cGUiOjEsInVzZXJBY2NvdW50IjoibGl1YnkiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwic3lzdGVtVHlwZSI6IjMiLCJ1c2VyVHlwZU5vdyI6IjMiLCJleHAiOjE2MTAwMzE4ODEsImp0aSI6IjUzOGNmODQ4LTg3MmEtNGQzMy1iMDU4LTM0YWE4NTRiM2E1NiJ9.AkQ16KfjN0FmR2X5tW0mxI21uTQN4F3laQJZ3JjBPD-53qBJrnXdKsJb53z5ti-sYCbx7b_-A-aM41EnWcmNK-WZRtiZh1DPvR34lGXPO1qJgJV77gqZENsdzgL8WcgkNjNgu9GTJeOmBITmAX6I9hncxjAGky5ikQY5oVxflHcBOjryU4zzG6gjY0cpdKswpyO_Z-sZeU4BIO_FxGkEgB_U5FkXp_U4Fnemda2uVNmw-K07TPGqX3Ive54GlagU2MDiCDs0DOozXOoZReoviJkyjB742PBD5HP_6uZiEqtcKVrMa5tNKfq3QuyVHEfQslMQASer4Q1WdBJFgKyFTw";
        String gateway = "http://39.104.85.103/";
        List<String> urls = new ArrayList<String>();
        urls.add("gateway/gpx-archive/api/v1/archive/project/notice/superviseWarning/project/data/push/second");
        urls.add("gateway/gpx-bidconfirm/bidconfirm/push/supervision");
        urls.add("gateway/gpe-evaluation/toWarnService/pushCommentIndex");
        urls.add("gateway/gpe-evaluation/toWarnService/pushExpertInfo");
        urls.add("gateway/gpe-evaluation/toWarnService/pushCommentResult");
        for (String projectGuId : bidItemGuidMap.keySet()) {
            System.out.println(projectGuId);
            Map date = new HashMap();
            date.put("projectId", projectGuId);
            try {
                HttpResponse res = HttpRequest.post(gateway+urls.get(0)).header("access_token",token).form(date).timeout(100000).execute();
                System.out.println(res.body());
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                List<String> date1 = new ArrayList<String>();
                date1.addAll(bidItemGuidMap.get(projectGuId));
                HttpResponse res1 = HttpRequest.post(gateway + urls.get(1)).header("access_token", token).body(JSON.toJSONString(date1)).timeout(400000).execute();
                System.out.println(res1.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Map date2 = new HashMap();
                date2.put("projectId", projectGuId);
                HttpResponse res2 = HttpRequest.post(gateway + urls.get(2)).header("access_token", token).form(date2).timeout(100000).execute();
                System.out.println(res2.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Map date3 = new HashMap();
                date3.put("projectId", projectGuId);
                HttpResponse res3 = HttpRequest.post(gateway + urls.get(3)).header("access_token", token).form(date3).timeout(100000).execute();
                System.out.println(res3.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Map date4 = new HashMap();
                date4.put("projectId", projectGuId);
                HttpResponse res4 = HttpRequest.post(gateway + urls.get(4)).header("access_token", token).form(date4).timeout(100000).execute();
                System.out.println(res4.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

    }
}
